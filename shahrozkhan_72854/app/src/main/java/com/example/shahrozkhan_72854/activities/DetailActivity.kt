package com.example.shahrozkhan_72854.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.ViewAllModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

private lateinit var quantity: TextView
private var totalQuantity = 1
private var totalPrice = 0
private lateinit var detailImg: ImageView
private lateinit var price: TextView
private lateinit var rating: TextView
private lateinit var description: TextView
private lateinit var addToCart: Button
private lateinit var addItem: ImageView
private lateinit var removeItem: ImageView
private var viewAllModel: ViewAllModel? = null
private lateinit var firestore: FirebaseFirestore
private lateinit var auth: FirebaseAuth

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val objectExtra = intent.getSerializableExtra("detail")
        if (objectExtra is ViewAllModel) {
        viewAllModel = objectExtra
        }

        quantity = findViewById(R.id.quantity)
        detailImg = findViewById(R.id.detailed_img)
        price = findViewById(R.id.detail_price)
        rating = findViewById(R.id.detail_rating)
        description = findViewById(R.id.detail_description_view)
        addToCart = findViewById(R.id.add_to_cart)
        addItem = findViewById(R.id.add_item)
        removeItem = findViewById(R.id.remove_item)

        viewAllModel?.let {
        Glide.with(applicationContext).load(it.image_url).into(detailImg)
        rating.text = it.rating
        description.text = it.description
        price.text = "Price: $${it.price}"

        val priceInt = it.price?.toInt()
                if (priceInt != null) {
                        totalPrice = priceInt * totalQuantity
                }
        }

        addToCart.setOnClickListener {
        addedToCart()
        }

        addItem.setOnClickListener {
        if (totalQuantity < 10) {
        totalQuantity++
        quantity.text = totalQuantity.toString()
        viewAllModel?.let {
        val priceInt = it.price?.toInt()
                if (priceInt != null) {
                        totalPrice = priceInt * totalQuantity
                }
        }
        }
        }

        removeItem.setOnClickListener {
        if (totalQuantity > 1) {
        totalQuantity--
        quantity.text = totalQuantity.toString()
        viewAllModel?.let {
        val priceInt = it.price?.toInt()
                if (priceInt != null) {
                        totalPrice = priceInt * totalQuantity
                }
        }
        }
        }
        }

private fun addedToCart() {
        val calForDate = Calendar.getInstance()

        val currentDate = SimpleDateFormat("MM dd, yyyy", Locale.getDefault()).format(calForDate.time)
        val currentTime = SimpleDateFormat("HH:mm:ss a", Locale.getDefault()).format(calForDate.time)

        val cartMap = hashMapOf(
        "productName" to viewAllModel?.name,
        "productPrice" to price.text.toString(),
        "currentDate" to currentDate,
        "currentTime" to currentTime,
        "totalQuantity" to quantity.text.toString(),
        "totalPrice" to totalPrice
        )

        firestore.collection("CurrentUser").document(auth.currentUser!!.uid)
        .collection("AddToCart").add(cartMap)
        .addOnCompleteListener { task ->
        if (task.isSuccessful) {
        Toast.makeText(this@DetailActivity, "Added to Cart.", Toast.LENGTH_SHORT).show()
        finish()
        }
        }
        }
        }
