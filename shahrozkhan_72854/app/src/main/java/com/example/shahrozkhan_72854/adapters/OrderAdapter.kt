package com.example.shahrozkhan_72854.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.OrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrderAdapter(var context: Context, var orderModelList: MutableList<OrderModel?>?) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    var totalPrice = 0
    var firestore: FirebaseFirestore
    var auth: FirebaseAuth

    init {
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = orderModelList?.get(position)?.productName
        holder.price.text = orderModelList?.get(position)?.productPrice
        holder.date.text = orderModelList?.get(position)?.currentDate
        holder.time.text = orderModelList?.get(position)?.currentTime
        holder.quantity.text = orderModelList?.get(position)?.totalQuantity
        holder.totalPrice.text = orderModelList?.get(position)?.totalPrice.toString()
    }

    override fun getItemCount(): Int {
        return orderModelList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var price: TextView
        var date: TextView
        var time: TextView
        var quantity: TextView
        var totalPrice: TextView

        init {
            name = itemView.findViewById(R.id.orderproduct_name)
            price = itemView.findViewById(R.id.orderproduct_price)
            date = itemView.findViewById(R.id.ordercurrent_date)
            time = itemView.findViewById(R.id.ordercurrent_time)
            quantity = itemView.findViewById(R.id.ordertotal_quantity)
            totalPrice = itemView.findViewById(R.id.ordertotal_price)
        }
    }
}
