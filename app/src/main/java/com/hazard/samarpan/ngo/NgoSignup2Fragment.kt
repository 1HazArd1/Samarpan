package com.hazard.samarpan.ngo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.hazard.samarpan.R

class NgoSignup2Fragment : Fragment(){
    private var v : View?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = layoutInflater.inflate(R.layout.ngo_signup2_fragment,container,false)

        val types = resources.getStringArray(R.array.organisation_type)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,types)
        val autoCompleteTV= v?.findViewById<AutoCompleteTextView>(R.id.ngosignup_select_orgtype_items)
        autoCompleteTV?.setAdapter(arrayAdapter)

        return v
    }
}