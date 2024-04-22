package com.example.shahrozkhan_72854.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.activities.PlacedOrderActivity
import com.example.shahrozkhan_72854.adapters.CartAdapter
import com.example.shahrozkhan_72854.models.CartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class CartFragment : Fragment() {
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
    var recyclerView: RecyclerView? = null
    var cartAdapter: CartAdapter? = null
    var cartModelList: MutableList<CartModel?>? = null
    var overTotalAmount: TextView? = null
    var buyNow: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_cart, container, false)

        // Set the toolbar title
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Cart")
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        recyclerView = root.findViewById(R.id.recView)
        buyNow = root.findViewById(R.id.buy)
        recyclerView?.setLayoutManager(LinearLayoutManager(activity))
        overTotalAmount = root.findViewById(R.id.textView6)
        cartModelList = ArrayList()
        cartAdapter = CartAdapter(requireActivity(), cartModelList)
        recyclerView?.setAdapter(cartAdapter)
        firestore!!.collection("CurrentUser").document(auth!!.currentUser!!.uid)
            .collection("AddToCart").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (documentSnapshot in task.result.documents) {
                        val documentId = documentSnapshot.id
                        val cartModel = documentSnapshot.toObject(CartModel::class.java)
                        cartModel!!.documentId = documentId
                        cartModelList?.add(cartModel)
                        cartAdapter!!.notifyDataSetChanged()
                    }
                    calculateTotalAmount(cartModelList as ArrayList<CartModel?>)
                }
            }
        buyNow?.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PlacedOrderActivity::class.java)
            intent.putExtra("itemList", cartModelList as Serializable?)
            startActivity(intent)
        })
        return root
    }

    private fun calculateTotalAmount(cartModelList: List<CartModel?>) {
        var totalAmount = 0.0
        for (cartModel in cartModelList) {
            totalAmount += cartModel!!.totalPrice.toDouble()
        }
        overTotalAmount!!.text = "Total Amount: $$totalAmount"
    }
}