package com.hazard.samarpan.ngo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.hazard.samarpan.R

class NgoSignup1Fragment : Fragment() {

    private lateinit var nextBtn: Button

    private var orgName : TextInputLayout ?=null
    private var orgMail : TextInputLayout ?=null
    private var orgPhone : TextInputLayout ?=null
    private var officeAdd :TextInputLayout ?=null
    private var orgPinCode :TextInputLayout ?=null
    private var pass :TextInputLayout ?=null
    private var confirmPass :TextInputLayout ?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = layoutInflater.inflate(R.layout.ngo_signup1_fragment, container, false)

        nextBtn = v.findViewById(R.id.btn_ngosignup1_next)
        nextBtn.setOnClickListener {

            orgName = v?.findViewById(R.id.et_ngosignup_name)
            orgMail = v?.findViewById(R.id.et_ngosignup_mail)
            orgPhone = v?.findViewById(R.id.et_ngosignup_phone)
            officeAdd = v?.findViewById(R.id.et_ngosignup_address)
            orgPinCode = v?.findViewById(R.id.et_ngosignup_pincode)
            pass =v?.findViewById(R.id.et_ngosignup_password)
            confirmPass =v?.findViewById(R.id.et_ngosignup_confirmpassword)

            //this opens up the sign up 2 page
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragment_container1,
                    NgoSignup2Fragment()
                )?.addToBackStack(null)?.commit()
        }

        return v
    }
}