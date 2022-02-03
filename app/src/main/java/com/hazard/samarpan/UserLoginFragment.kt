package com.hazard.samarpan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class UserLoginFragment: Fragment() {
    private var itemView: View?=null

    private var btnSignUp: Button?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemView =layoutInflater.inflate(R.layout.user_login_fragment,container,false)

        btnSignUp=itemView!!.findViewById(R.id.userSignUp)

        btnSignUp?.setOnClickListener {
              val userRegistrationPage=UserRegisterFragment()
              activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.loginFragment,userRegistrationPage)
                  ?.addToBackStack(null)?.commit()
        }
        return itemView
    }
}