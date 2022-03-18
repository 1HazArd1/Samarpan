package com.hazard.samarpan.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hazard.samarpan.adapters.ClothAdapter
import com.hazard.samarpan.R

class UserDashboardFragment : Fragment() {

    private lateinit var clothRecycler: RecyclerView
    private var donate: FloatingActionButton?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = layoutInflater.inflate(R.layout.user_dashboard_fragment, container, false)

        donate = v.findViewById(R.id.btnDonate)

        var clothList: ArrayList<Int> = ArrayList()
        for (i in 1..15) {
            clothList.add(R.drawable.ic_uploadimage)
        }
        clothRecycler = v.findViewById(R.id.clothing_recycler)
        clothRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        clothRecycler.adapter = ClothAdapter(clothList)


        donate?.setOnClickListener {
            val donationInfo= DonationInfoFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container2,donationInfo)?.commit()
        }
        return v

    }
}