package com.hazard.samarpan

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class UserRegisterFragment : Fragment() {
    private var itemView: View? = null

    private var name: TextInputEditText? = null
    private var email: TextInputEditText? = null
    private var password: TextInputEditText? = null
    private var phoneNo: TextInputEditText? = null
    private var btnRegister: Button? = null
    private var btnSignIn: LinearLayout? = null
    private var etAddress: TextInputEditText? = null
    private var etPinCode: TextInputEditText? = null


    private lateinit var registerAuth: FirebaseAuth

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        itemView = layoutInflater.inflate(R.layout.user_registration_fragment, container, false)

        btnRegister = itemView?.findViewById(R.id.btnUserRegister)
        btnSignIn = itemView?.findViewById(R.id.btnRegisterSignIn)

        email = itemView?.findViewById(R.id.registerEmail)
        password = itemView?.findViewById(R.id.registerPassword)
        name = itemView?.findViewById(R.id.registerName)
        phoneNo = itemView?.findViewById(R.id.registerPhone)
        etAddress = itemView?.findViewById(R.id.registerAddress)
        etPinCode = itemView?.findViewById(R.id.registerPinCode)


        registerAuth = Firebase.auth


        btnSignIn?.setOnClickListener {
            val userLogin = LoginFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container1, userLogin)?.commit()
        }

        btnRegister?.setOnClickListener {

            val mail = email?.text.toString().trim()
            val pass = password?.text.toString().trim()
            val donorName = name?.text.toString().trim()
            val donorPhoneNo = phoneNo?.text.toString().trim()
            val donorAddress = etAddress?.text.toString()
            val donorPinCode = etPinCode?.text.toString()

            val db = FirebaseFirestore.getInstance()

            if (mail.isEmpty()) {
                email?.error = "Email cannot be empty"
            }
            if (pass.isEmpty()) {
                password?.error = "Password cannot be empty"
            }
            if (donorName.isEmpty()) {
                name?.error = "Enter the name"
            }
            if (donorPhoneNo.isEmpty()) {
                phoneNo?.error = "Enter the proper phone number"
            }
            if (donorAddress.isEmpty()) {
                etAddress?.error = "Enter the complete address"
            }
            if (donorPinCode.isEmpty()) {
                etPinCode?.error = "Enter your area pin code"
            }
            if (!(mail.matches(emailPattern.toRegex()))) {
                email?.error = "Invalid email"
            }
            if (!(isValidPassword(pass))) {
                password?.error = "Password format is invalid"
            } else {
                //user registration
                try {

                    registerAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener { task ->

                            //if registration is done successfully
                            if (task.isSuccessful) {
                                val user: FirebaseUser? =registerAuth.currentUser
                                Toast.makeText(
                                    activity,
                                    "You were registered successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                //collecting the user data from the app with the help of the below method
                                val documentReference :DocumentReference=db.collection("users").document(
                                    user?.uid ?: ""
                                )
                                val donorInfo: MutableMap<String, Any> = HashMap()
                                donorInfo["Full Name"] = donorName
                                donorInfo["Email"] = mail
                                donorInfo["PhoneNumber"] = donorPhoneNo
                                donorInfo["Address"] = donorAddress
                                donorInfo["Pin Code"] = donorPinCode
                                //specify if the user is donor
                                donorInfo["isDonor"] = 1  // in case of NGO give this value 0

                                documentReference.set(donorInfo).addOnSuccessListener {
                                    Log.d(TAG, "User data for $donorName was collected successfully ")
                                }.addOnFailureListener{ e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                                /* add the intent to go to the main dashboard of the application and also add finish() so that
                                    if the user presses back after registration they don't go back to the registration page
                                    it basically clears all the previous activities
                                */
                                activity?.let{
                                    val intent = Intent (it, Main2Activity::class.java)
                                    it.startActivity(intent)
                                }
                                activity?.finish()  /* as the fragment does not have finish function this function finds the
                                                      activity to which the fragment is attached and finishes it */
                            }
                            // if the registration failed somehow
                            else {
                                Toast.makeText(
                                    activity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }

                } catch (e: Exception) {
                    Toast.makeText(context, "Fields cannot be empty $e", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return itemView
    }


    private fun isValidPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        )
        return passwordREGEX.matcher(password).matches()
    }
}