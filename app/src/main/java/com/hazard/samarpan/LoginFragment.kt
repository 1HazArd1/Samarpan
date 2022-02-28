package com.hazard.samarpan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment: Fragment() {
    private var itemView: View?=null
    private var userSignUp: LinearLayout?=null
    private var email: TextInputEditText?=null
    private var password: TextInputEditText?=null
    private var btnLogin: Button?=null

    private lateinit var ngoSignUp: TextView

    private lateinit var loginAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemView =layoutInflater.inflate(R.layout.login_fragment,container,false)

        email=itemView?.findViewById(R.id.donor_email)
        password=itemView?.findViewById(R.id.donor_password)
        userSignUp=itemView!!.findViewById(R.id.userSignUp)
        btnLogin=itemView!!.findViewById(R.id.userLogin)

        ngoSignUp = itemView!!.findViewById(R.id.tv_login_registerasngo)

        loginAuth= Firebase.auth

        ngoSignUp.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container1, NgoSignup1Fragment())?.addToBackStack(null)?.commit()
        }

        userSignUp?.setOnClickListener {
              val userRegistrationPage=UserRegisterFragment()
              activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container1,userRegistrationPage)
                  ?.commit()
        }

        btnLogin?.setOnClickListener {
            val mail=email?.text.toString().trim()
            val pass=password?.text.toString().trim()
            if(mail.isEmpty()){
                email?.error = "Email cannot be empty"
            }
            if(pass.isEmpty()){
                password?.error = "Password cannot be empty"
            }
            else{
                try {
                    loginAuth.signInWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                // logic for navigating to the dashboard
                                /* here based on the logic of the type of user the respective activity would be loaded
                                    i.e is the user ngo or donor */
                                Toast.makeText(
                                    activity,
                                    "Logged in Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                activity?.let{
                                    val intent = Intent (it, Main2Activity::class.java)
                                    it.startActivity(intent)
                                }
                                activity?.finish()

                            } else {
                                Toast.makeText(
                                    activity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
                catch (e:IllegalArgumentException){
                    Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_SHORT)
                }
            }
        }

        return itemView
    }

    override fun onStart() {
        super.onStart()
        // check if the user is signed in then don't open login or register page directly send to the required activity
        val currentUser = loginAuth.currentUser
        if (currentUser != null) {
            // write logic to send to the main dashboard of the application
            activity?.let{
                val intent =Intent(it,Main2Activity::class.java)
                startActivity(intent)
            }
        }
    }
    
}