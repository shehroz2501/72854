package com.example.shahrozkhan_72854.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shahrozkhan_72854.R
import com.example.shahrozkhan_72854.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    var profileImg: CircleImageView? = null
    var name: EditText? = null
    var email: EditText? = null
    var number: EditText? = null
    var address: EditText? = null
    var update: Button? = null
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var storage: FirebaseStorage? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        profileImg = root.findViewById(R.id.profile_img)
        name = root.findViewById(R.id.profile_name)
        email = root.findViewById(R.id.profile_email)
        number = root.findViewById(R.id.profile_number)
        address = root.findViewById(R.id.profile_address)
        update = root.findViewById(R.id.updateBtn)

        // Fetch user data from Firebase Realtime Database
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userModel = snapshot.getValue(UserModel::class.java)
                    if (userModel != null) {
                        name?.setText(userModel.name)
                        email?.setText(userModel.email)
                        number?.setText(userModel.number)
                        address?.setText(userModel.address)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userModel = snapshot.getValue(UserModel::class.java)
                    Glide.with(requireContext()).load(userModel?.profileImg).into(profileImg!!)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        profileImg?.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 33)
        })
        update?.setOnClickListener(View.OnClickListener { updateUserProfile() })
        return root
    }

    private fun updateUserProfile() {
        // Get the updated data
        val updatedName = name!!.text.toString()
        val updatedEmail = email!!.text.toString()
        val updatedNumber = number!!.text.toString()
        val updatedAddress = address!!.text.toString()

        // Update the user profile in the database
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .child("name").setValue(updatedName)
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .child("email").setValue(updatedEmail)
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .child("number").setValue(updatedNumber)
        database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
            .child("address").setValue(updatedAddress)

        // Show a toast message or perform any other actions as needed
        Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!!.data != null) {
            val profileURI = data.data
            profileImg!!.setImageURI(profileURI)
            val reference = storage!!.reference.child("profile picture")
                .child(FirebaseAuth.getInstance().uid!!)
            reference.putFile(profileURI!!).addOnSuccessListener {
                Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show()
                reference.downloadUrl.addOnSuccessListener { uri ->
                    database!!.reference.child("Users").child(FirebaseAuth.getInstance().uid!!)
                        .child("profileImg").setValue(uri.toString())
                    Toast.makeText(context, "Profile Picture Uploaded", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}