
package com.faircorp.android.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.OnRoomSelectedListener
import com.faircorp.android.R


class RoomAdapter(var listener: OnRoomSelectedListener) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomName: TextView = view.findViewById(R.id.txt_room_name)
        val roomId: TextView = view.findViewById(R.id.txt_room_id)
        val roomCurrentTemp: TextView = view.findViewById(R.id.txt_room_current_temperature)
        val roomTargetTemp: TextView = view.findViewById(R.id.txt_room_target_temperature)
        val buildingId: TextView = view.findViewById(R.id.txt_building_id)
    }

    private val items = mutableListOf<RoomDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_room_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            val stringBuilder = StringBuilder()
            roomName.text = stringBuilder.append("Name: ").append(room.name)
            stringBuilder.clear()
            roomId.text = stringBuilder.append("Room ID: ").append(room.id.toString())
            stringBuilder.clear()
            roomCurrentTemp.text = stringBuilder.append("C.T: ").append( room.currentTemperature.toString()).append("°C")
            stringBuilder.clear()
            roomTargetTemp.text = stringBuilder.append("T.T: ").append( room.targetTemperature.toString()).append("°C")
            stringBuilder.clear()
            buildingId.text = stringBuilder.append("Building ID: ").append(room.buildingId.toString())
            stringBuilder.clear()
            itemView.setOnClickListener {
                listener.onRoomSelected(room.id)
            }
        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}