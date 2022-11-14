package com.faircorp.android.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.OnHeaterSelectedListener
import com.faircorp.android.R

class HeaterAdapter(var listener: OnHeaterSelectedListener) :
    RecyclerView.Adapter<HeaterAdapter.HeaterViewHolder>() {

    inner class HeaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val heaterName: TextView = view.findViewById(R.id.txt_heater_room_id)
        val heaterModel: TextView = view.findViewById(R.id.txt_heater_model)
        val heaterStatus: TextView = view.findViewById(R.id.txt_hater_status)
        val heaterCurrentTemp: TextView = view.findViewById(R.id.txt_heater_current_temp)
        val heaterTargetTemp: TextView = view.findViewById(R.id.txt_heater_target_temp)
        val heaterId: TextView = view.findViewById(R.id.txt_heater_id)

    }

    val items = mutableListOf<HeaterDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(heaters: List<HeaterDto>) {
        items.clear()
        items.addAll(heaters)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_heater_item, parent, false)
        return HeaterViewHolder(view)
    }


    override fun onBindViewHolder(holder: HeaterViewHolder, position: Int) {
        val heater = items[position]
        holder.apply {
            val stringBuilder = StringBuilder()
            heaterName.text = stringBuilder.append("Room ID: ").append(heater.roomId)
            stringBuilder.clear()
            heaterStatus.text = heater.heaterStatus.toString()
            heaterModel.text = stringBuilder.append("Name: ").append(heater.name)
            stringBuilder.clear()
            heaterCurrentTemp.text =
                stringBuilder.append("C.T: ").append(heater.currentTemperature.toString())
                    .append("°C")
            stringBuilder.clear()
            heaterId.text = stringBuilder.append("Heater ID: ").append(heater.id.toString())
            stringBuilder.clear()
            heaterTargetTemp.text =
                stringBuilder.append("T.T: ").append(heater.targetTemperature.toString())
                    .append("°C")
            stringBuilder.clear()
            itemView.setOnClickListener {
                listener.onHeaterSelected(heater.id)
            }
        }
    }

    override fun onViewRecycled(holder: HeaterViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}
