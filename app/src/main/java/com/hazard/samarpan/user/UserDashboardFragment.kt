package com.hazard.samarpan.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hazard.samarpan.adapters.ClothAdapter
import com.hazard.samarpan.R
import com.hazard.samarpan.common.MainActivity


class UserDashboardFragment : Fragment() {

    private lateinit var clothRecycler: RecyclerView
    private var donate: FloatingActionButton?= null
    private var btnLogout : ImageButton?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = layoutInflater.inflate(R.layout.user_dashboard_fragment, container, false)

        donate = v.findViewById(R.id.btnDonate)

        btnLogout=v.findViewById(R.id.profile_button)

        val clothList: ArrayList<Int> = ArrayList()
        for (i in 1..15) {
            clothList.add(R.drawable.ic_uploadimage)
        }
        clothRecycler = v.findViewById(R.id.clothing_recycler)
        clothRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        clothRecycler.adapter = ClothAdapter(clothList)

        btnLogout?.setOnClickListener{
            val intent = Intent(context,UserProfileActivity::class.java)
            startActivity(intent)
        }

        donate?.setOnClickListener {
            val donationInfo= DonationInfoFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container2,donationInfo)?.commit()
        }
        return v

    }
}