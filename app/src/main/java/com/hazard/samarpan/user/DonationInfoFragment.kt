package com.hazard.samarpan.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hazard.samarpan.R

class DonationInfoFragment: Fragment() {

    private var itemView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemView=layoutInflater.inflate(R.layout.donation_info_fragment,container,false)

        return itemView
    }
}