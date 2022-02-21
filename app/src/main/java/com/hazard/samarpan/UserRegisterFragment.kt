package com.hazard.samarpan

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class UserRegisterFragment: Fragment(){
    private var itemView:View?=null

    private var name: TextInputEditText?=null
    private var email: TextInputEditText?=null
    private var password: TextInputEditText?=null
    private var phoneNo: TextInputEditText?=null
    private var btnRegister : Button?=null
    private var btnSignIn: Button?=null

    private lateinit var registerAuth: FirebaseAuth

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        itemView=layoutInflater.inflate(R.layout.user_registration_fragment,container,false)

        btnSignIn=itemView?.findViewById(R.id.registerSignIn)
        btnRegister=itemView?.findViewById(R.id.btnUserRegister)
        email=itemView?.findViewById(R.id.registerEmail)
        password=itemView?.findViewById(R.id.registerPassword)
        name=itemView?.findViewById(R.id.registerName)
        phoneNo=itemView?.findViewById(R.id.registerPhone)

        registerAuth= Firebase.auth

        btnSignIn?.setOnClickListener {
            val userLogin=UserLoginFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.loginFragment,userLogin)?.commit()
        }
        btnRegister?.setOnClickListener{
            val mail=email?.text.toString().trim()
            val pass=password?.text.toString().trim()
            if(TextUtils.isEmpty(mail)){
                email?.error = "Email cannot be empty"
            }
            if(TextUtils.isEmpty(pass)){
                password?.error = "Password cannot be empty"
            }
            if (!(mail.matches(emailPattern.toRegex()))) {
                email?.error="Invalid email"
            }
            if(!(isValidPassword(pass))){
                password?.error="Password format is invalid"
            }
            else{
                //user registration
                registerAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(
                    OnCompleteListener <AuthResult>{ task ->

                        //if registration is done successfully
                        if(task.isSuccessful){
                            Toast.makeText(activity,"You were registered successfully.", Toast.LENGTH_SHORT).show()
                            /* add the intent to go to the main dashboard of the application and also add finish() so that
                               if the user presses back after registration they don't go back to the registration page
                               it basically clears all the previous activities
                             */
                        }
                        // if the registration failed somehow
                        else{
                            Toast.makeText(activity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT).show()
                        }

                    }
                )
            }
        }
        return itemView
    }
    override fun onStart() {
        super.onStart()
        // check if the user is signed in then don't open login or register page directly send to the required activity
        val currentUser=registerAuth.currentUser
        if(currentUser !=null){
            // write logic to send to the main dashboard of the application
        }
    }
    private fun isValidPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$")
        return passwordREGEX.matcher(password).matches()
    }
}