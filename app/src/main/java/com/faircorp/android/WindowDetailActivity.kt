package com.faircorp.android

import android.os.Bundle
import android.widget.TextView
import com.faircorp.android.model.DefaultDataBase

class WindowDetailActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val windowId = this.intent.getLongExtra(WINDOW_NAME_PARAM, 1)
        val window = DefaultDataBase.findWindowById(windowId)
        if (window != null) {
            findViewById<TextView>(R.id.txt_window_name).text = window.name
            findViewById<TextView>(R.id.txt_window_room_name).text = window.room.name
            findViewById<TextView>(R.id.txt_window_current_temperature).text =
                window.room.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_target_temperature).text =
                window.room.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
        }
    }
}