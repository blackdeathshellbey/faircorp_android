package com.faircorp.android.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.OnBuildingSelectedListener
import com.faircorp.android.R

class BuildingAdapter(var listener: OnBuildingSelectedListener) :
    RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    inner class BuildingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buildingId: TextView = view.findViewById(R.id.txt_buildings_id)
        val buildingName: TextView = view.findViewById(R.id.txt_building_name)
        val buildingAddress: TextView = view.findViewById(R.id.txt_building_address)
        val buildingCity: TextView = view.findViewById(R.id.txt_building_city)
        val buildingZipCode: TextView = view.findViewById(R.id.txt_building_zip_code)

    }

    val items = mutableListOf<BuildingDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(buildings: List<BuildingDto>) {
        items.clear()
        items.addAll(buildings)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_building_item, parent, false)
        return BuildingViewHolder(view)
    }


    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val building = items[position]
        holder.apply {
            val stringBuilder = StringBuilder()
            buildingId.text = stringBuilder.append("Building ID: ").append(building.id.toString())
            stringBuilder.clear()
            buildingName.text = stringBuilder.append("Name: ").append(building.name)
            stringBuilder.clear()
            buildingAddress.text = stringBuilder.append("Address: ").append(building.adress)
            stringBuilder.clear()
            buildingCity.text = stringBuilder.append("City:").append(building.city)
            stringBuilder.clear()
            buildingZipCode.text = stringBuilder.append("ZipCode: ").append(building.zipCode.toString())
            stringBuilder.clear()
            itemView.setOnClickListener {
                listener.onBuildingSelected(building.id)
            }
        }
    }

    override fun onViewRecycled(holder: BuildingViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}
