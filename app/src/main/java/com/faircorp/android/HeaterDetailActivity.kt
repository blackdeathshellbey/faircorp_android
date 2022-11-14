package com.faircorp.android

import android.os.Bundle
import android.widget.TextView
import com.faircorp.android.model.DefaultDataBase

class HeaterDetailActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = this.intent.getLongExtra(HEATER_NAME_PARAM, 2)
        val heater = DefaultDataBase.findHeaterById(id)
        if (heater != null) {
            findViewById<TextView>(R.id.txt_heater_room_id).text = heater.roomId
            findViewById<TextView>(R.id.txt_heater_model).text = heater.name
            findViewById<TextView>(R.id.txt_hater_status).text = heater.heaterStatus.toString()
            findViewById<TextView>(R.id.txt_heater_id).text = heater.id.toString()
            findViewById<TextView>(R.id.txt_heater_target_temp).text = heater.targetTemperature.toString()
            findViewById<TextView>(R.id.txt_heater_current_temp).text = heater.currentTemperature.toString()
        }
    }
}
