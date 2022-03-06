package com.hazard.samarpan
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardFragment : Fragment() {

    public lateinit var clothRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = layoutInflater.inflate(R.layout.dashboard_fragment, container, false)

        var clothList: ArrayList<Int> = ArrayList()
        for(i in 1..15){
            clothList.add(R.drawable.ic_uploadimage)
        }


        clothRecycler = v.findViewById(R.id.clothing_recycler)
        clothRecycler.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        clothRecycler.adapter=ClothAdapter(clothList)


        return v

    }
}