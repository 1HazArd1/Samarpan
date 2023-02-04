package com.hazard.samarpan.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hazard.samarpan.R
import com.hazard.samarpan.common.MainActivity

class UserProfileActivity : AppCompatActivity() {
    private lateinit var registerAuth: FirebaseAuth
    private var TAG : String = "UserProfileActivity"
    private var userName: TextView? = null
    private var userPhone: TextView? = null
    private var userAddress: TextView? = null
    private var userEmail: TextView? = null
    private var userPin: TextView? = null
    private var logOutBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        registerAuth = FirebaseAuth.getInstance()
        userName = findViewById(R.id.user_profile_name)
        userPhone = findViewById(R.id.user_profile_phone)
        userAddress = findViewById(R.id.user_profile_address)
        userEmail = findViewById(R.id.user_profile_emailid)
        userPin = findViewById(R.id.user_profile_pin)
        logOutBtn = findViewById(R.id.userLogout)
        logOutBtn?.setOnClickListener{
            registerAuth.signOut()
            Toast.makeText(this,"Logged out successfully!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        (super.onResume())
        readUserData()
    }

    private fun readUserData()
    {
        val dbInstance = Firebase.firestore
        val user: FirebaseUser? = registerAuth.currentUser
        val docRef = dbInstance.collection("users").document(
            user?.uid ?: ""
        )
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    userName?.text = document.data?.get("Full Name").toString()
                    userPhone?.text = document.data?.get("PhoneNumber").toString()
                    userAddress?.text  = document.data?.get("Address").toString()
                    userPin?.text = document.data?.get("Pin Code").toString()
                    userEmail?.text = document.data?.get("Email").toString()
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}