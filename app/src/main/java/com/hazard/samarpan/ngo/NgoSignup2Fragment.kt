package com.hazard.samarpan.ngo

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.hazard.samarpan.R

class NgoSignup2Fragment : Fragment(){

    private var v : View?=null
    private var btnNgoRegister: Button?=null
    private var tvSample :TextView?=null

    private var orgName:String =""
    private var orgMail:String =""
    private var orgPhone:String =""
    private var officeAdd:String =""
    private var orgPin:String =""
    private var pass:String =""
    private lateinit var registerAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = layoutInflater.inflate(R.layout.ngo_signup2_fragment,container,false)

        btnNgoRegister=v?.findViewById(R.id.btnNgoRegister)
        tvSample=v?.findViewById(R.id.tv_Sample)

        val organisationTypes = resources.getStringArray(R.array.organisation_type)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,organisationTypes)
        val autoCompleteTV= v?.findViewById<AutoCompleteTextView>(R.id.ngosignup_select_orgtype_items)
        autoCompleteTV?.setAdapter(arrayAdapter)



        registerAuth= Firebase.auth

        orgName=arguments?.getString("Name").toString()
        orgMail= arguments?.getString("Mail").toString()
        orgPhone=arguments?.getString("Phone").toString()
        officeAdd=arguments?.getString("Address").toString()
        orgPin=arguments?.getString("PinCode").toString()
        pass=arguments?.getString("Password").toString()

        btnNgoRegister?.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            try{
                registerAuth.createUserWithEmailAndPassword(orgMail,pass)
                    .addOnCompleteListener{ task->
                        if(task.isSuccessful){
                         val ngo: FirebaseUser? =registerAuth.currentUser
                            Toast.makeText(
                                activity,
                                "Thank You! for choosing us",
                                Toast.LENGTH_SHORT
                             ).show()
                            val documentReference : DocumentReference =db.collection("users").document(
                                ngo?.uid ?: ""
                         )
                            val ngoInfo :MutableMap<String,Any> = HashMap()
                            ngoInfo["OrgName"]= orgName
                            ngoInfo["OrgMail"]= orgMail
                            ngoInfo["OrgPhone"]=orgPhone
                            ngoInfo["OfficeAdd"]=officeAdd
                            ngoInfo["OrgPinCode"]=orgPin
                            ngoInfo["isDonor"]=0
                            // add the value of the org type from the dropdown and upload document work
                            documentReference.set(ngoInfo).addOnSuccessListener {
                                Log.d(ContentValues.TAG, "User data for $orgName was collected successfully ")
                            }.addOnFailureListener{ e ->
                                Log.w(ContentValues.TAG, "Error adding data", e)
                            }
                    }else{
                            Toast.makeText(
                                activity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
        }
        catch(e :Exception){
            Toast.makeText(context, "Fields cannot be empty $e", Toast.LENGTH_SHORT).show()
        }
        }
        return v
    }
}