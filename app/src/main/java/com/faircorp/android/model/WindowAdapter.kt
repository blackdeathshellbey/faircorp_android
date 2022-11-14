package com.faircorp.android.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.OnWindowSelectedListener
import com.faircorp.android.R


class WindowAdapter(var listener: OnWindowSelectedListener) :
    RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() {

    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val windowName: TextView = view.findViewById(R.id.txt_window_name)
        val windowRoomName: TextView = view.findViewById(R.id.txt_window_room_name)
        val windowStatus: TextView = view.findViewById(R.id.txt_window_status)
        val windowCurrentTemp: TextView = view.findViewById(R.id.txt_window_current_temperature)
        val windowTargetTemp: TextView = view.findViewById(R.id.txt_window_target_temperature)
    }

    val items = mutableListOf<WindowDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(windows: List<WindowDto>) {
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_window_item, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {
        val window = items[position]
        holder.apply {
            val stringBuilder = StringBuilder()
            windowName.text = stringBuilder.append("Window Name: ") .append(window.name)
            stringBuilder.clear()
            windowStatus.text = window.windowStatus.toString()
            stringBuilder.clear()
            windowRoomName.text = stringBuilder.append("Room Name: ") .append(window.room.name)
            stringBuilder.clear()
            windowCurrentTemp.text = stringBuilder.append("C.T: ").append( window.room.currentTemperature.toString()).append("°C")
            stringBuilder.clear()
            windowTargetTemp.text = stringBuilder.append("T.T: ").append( window.room.targetTemperature.toString()).append("°C")
            stringBuilder.clear()
            itemView.setOnClickListener {
                listener.onWindowSelected(window.id)
            }
        }
    }

    override fun onViewRecycled(holder: WindowViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}