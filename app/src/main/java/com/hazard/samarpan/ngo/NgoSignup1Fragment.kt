package com.hazard.samarpan.ngo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.hazard.samarpan.R

class NgoSignup1Fragment : Fragment() {

    private lateinit var nextBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = layoutInflater.inflate(R.layout.ngo_signup1_fragment, container, false)

        nextBtn = v.findViewById(R.id.btn_ngosignup1_next)
        nextBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fragment_container1,
                    NgoSignup2Fragment()
                )?.addToBackStack(null)?.commit()
        }

        return v
    }
}