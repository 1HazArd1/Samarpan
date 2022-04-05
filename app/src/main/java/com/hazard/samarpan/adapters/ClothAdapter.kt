package com.hazard.samarpan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hazard.samarpan.R

class ClothAdapter(private var clothList: ArrayList<Int>) :
    RecyclerView.Adapter<ClothAdapter.ViewHolder>() {

    override fun getItemCount() = clothList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.clothImage.setImageDrawable(holder.itemView.context.getDrawable(clothList[position]))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.clothing_item_dashboard, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clothImage: ImageView = itemView.findViewById(R.id.cloth_image)


    }
}

