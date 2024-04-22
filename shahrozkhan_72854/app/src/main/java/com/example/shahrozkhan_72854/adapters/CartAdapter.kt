package com.example.shahrozkhan_72854.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.CartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CartAdapter(var context: Context, var cartModelList: MutableList<CartModel?>?) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var totalPrice = 0
    var firestore: FirebaseFirestore
    var auth: FirebaseAuth

    init {
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = cartModelList?.get(position)?.productName
        holder.price.text = cartModelList?.get(position)?.productPrice
        holder.date.text = cartModelList?.get(position)?.currentDate
        holder.time.text = cartModelList?.get(position)?.currentTime
        holder.quantity.text = cartModelList?.get(position)?.totalQuantity
        holder.totalPrice.text = cartModelList?.get(position)?.totalPrice.toString()
        holder.deleteItem.setOnClickListener {
            firestore.collection("CurrentUser").document(auth.currentUser!!.uid)
                .collection("AddToCart")
                .document(cartModelList?.get(position)?.documentId!!)
                .delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        cartModelList?.remove(cartModelList!![position])
                        notifyDataSetChanged()
                        Toast.makeText(context, "Item Removed Successfully", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            "Error: " + task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun getItemCount(): Int {
        return cartModelList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var price: TextView
        var date: TextView
        var time: TextView
        var quantity: TextView
        var totalPrice: TextView
        var deleteItem: ImageView

        init {
            name = itemView.findViewById(R.id.product_name)
            price = itemView.findViewById(R.id.product_price)
            date = itemView.findViewById(R.id.current_date)
            time = itemView.findViewById(R.id.current_time)
            quantity = itemView.findViewById(R.id.total_quantity)
            totalPrice = itemView.findViewById(R.id.total_price)
            deleteItem = itemView.findViewById(R.id.delete)
        }
    }
}
