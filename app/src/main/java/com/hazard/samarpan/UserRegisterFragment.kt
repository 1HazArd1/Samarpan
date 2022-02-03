package com.hazard.samarpan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class UserRegisterFragment: Fragment(){
    private var itemView:View?=null

    private var btnSignIn: Button?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        itemView=layoutInflater.inflate(R.layout.user_registration_fragment,container,false)

        btnSignIn=itemView?.findViewById(R.id.registerSignIn)

        btnSignIn?.setOnClickListener {
            val userLogin=UserLoginFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.loginFragment,userLogin)?.commit()
        }
        return itemView
    }
}