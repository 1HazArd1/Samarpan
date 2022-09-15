package com.hazard.samarpan.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hazard.samarpan.R
import com.hazard.samarpan.ngo.Communicator
import com.hazard.samarpan.ngo.NgoSignup2Fragment

class MainActivity : AppCompatActivity(),Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*This part of the code is used to set the default theme as the theme was set to the splash screen before setting the main activity layout
           remove the comment tag for adding the logic*/
        setTheme(R.style.Theme_Samarpan)
        setContentView(R.layout.activity_main)
        addFragment()

    }

    private fun addFragment() {
        val userSignInLayout= LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container1,userSignInLayout).commit()
    }

    override fun passDataCom(
        orgName: String,
        orgMail: String,
        orgPhone: String,
        officeAdd: String,
        orgPin: String,
        orgPass: String
    ) {
        val bundle=Bundle()
        bundle.putString("Name",orgName)
        bundle.putString("Mail",orgMail)
        bundle.putString("Phone",orgPhone)
        bundle.putString("Address",officeAdd)
        bundle.putString("PinCode",orgPin)
        bundle.putString("Password",orgPass)

        val transaction=this.supportFragmentManager.beginTransaction()
        val ngoSignUp2 = NgoSignup2Fragment()
        ngoSignUp2.arguments = bundle
        transaction.commit()
    }
}