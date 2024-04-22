package com.example.shahrozkhan_72854.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    var signup: AppCompatButton? = null
    var name: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        signup = findViewById(R.id.signupBtn)
        name = findViewById(R.id.etsName)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPass)
        signup?.setOnClickListener(View.OnClickListener { createUser() })
    }

    private fun createUser() {
        val userName = name!!.text.toString()
        val userEmail = email!!.text.toString()
        val userPassword = password!!.text.toString()
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Name is empty.", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is empty.", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show()
        }
        auth!!.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userModel = UserModel(userName, userEmail, userPassword)
                    val id = task.result.user!!.uid
                    database!!.reference.child("Users").child(id).setValue(userModel)
                    Toast.makeText(
                        this@SignupActivity,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        this@SignupActivity,
                        "Error:" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}