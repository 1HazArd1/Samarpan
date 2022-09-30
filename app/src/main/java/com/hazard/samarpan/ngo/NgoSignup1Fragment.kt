package com.hazard.samarpan.ngo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.hazard.samarpan.R
import java.util.regex.Pattern

class NgoSignup1Fragment : Fragment() {

    private lateinit var communicator: Communicator
    private lateinit var nextBtn: Button

    private var name : TextInputEditText ?=null
    private var mail : TextInputEditText ?=null
    private var phone : TextInputEditText ?=null
    private var add :TextInputEditText ?=null
    private var pinCode :TextInputEditText ?=null
    private var password :TextInputEditText ?=null
    private var confirmPassword :TextInputEditText ?=null

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = layoutInflater.inflate(R.layout.ngo_signup1_fragment, container, false)

        communicator = activity as Communicator

        nextBtn = v.findViewById(R.id.btn_ngosignup1_next)

        name = v?.findViewById(R.id.et_ngosignup_name)
        mail = v?.findViewById(R.id.et_ngosignup_mail)
        phone = v?.findViewById(R.id.et_ngosignup_phone)
        add = v?.findViewById(R.id.et_ngosignup_address)
        pinCode = v?.findViewById(R.id.et_ngosignup_pincode)
        password =v?.findViewById(R.id.et_ngosignup_password)
        confirmPassword =v?.findViewById(R.id.et_ngosignup_confirmpassword)

        nextBtn.setOnClickListener {


            val orgName = name?.text.toString().trim()
            val orgMail = mail?.text.toString().trim()
            val orgPhone = phone?.text.toString().trim()
            val officeAdd = add?.text.toString().trim()
            val orgPinCode = pinCode?.text.toString().trim()
            val orgPass = password?.text.toString().trim()
            val confirmPass=confirmPassword?.text.toString().trim()


            if (orgName.isEmpty()) {
                name?.error = "Please provide organisation's name"
                markButtonDisable(nextBtn)
            }
            if (orgMail.isEmpty()) {
                mail?.error = "Mail cannot be empty"
                markButtonDisable(nextBtn)
            }
            if (orgPhone.isEmpty()) {
                phone?.error = "Enter your phone number"
                markButtonDisable(nextBtn)
            }
            if (officeAdd.isEmpty()) {
                add?.error = "Address cannot be empty"
                markButtonDisable(nextBtn)
            }
            if (orgPinCode.isEmpty()) {
                name?.error = "Name cannot be empty"
                markButtonDisable(nextBtn)
            }
            if (orgPass.isEmpty()){
                password?.error = "Password cannot be empty"
                markButtonDisable(nextBtn)
            }
            if (!(orgMail.matches(emailPattern.toRegex()))) {
                mail?.error = "Invalid email"
                markButtonDisable(nextBtn)
            }
            if (!(isValidPassword(orgPass))) {
                password?.error = "Password format is invalid"
                markButtonDisable(nextBtn)
            }
            if(orgPass != confirmPass){
                confirmPassword?.error = "Passwords doesn't match"
                markButtonDisable(nextBtn)
            }else{
                communicator.passDataCom(orgName,orgMail,orgPhone,officeAdd,orgPinCode,orgPass)
                //this method already have the logic to navigate to sign up page 2
            }
        }

        return v
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
    private fun markButtonDisable(b:Button){
        b.isEnabled =false
        b.setBackgroundColor(resources.getColor(R.color.theme_button_disabled))
    }
}