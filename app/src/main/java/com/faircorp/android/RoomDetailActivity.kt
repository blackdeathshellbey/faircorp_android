package com.faircorp.android

import android.os.Bundle
import android.widget.TextView
import com.faircorp.android.model.DefaultDataBase

class RoomDetailActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roomId = this.intent.getLongExtra(ROOM_NAME_PARAM, 0)
        val room = DefaultDataBase.findRoomById(roomId)
        if (room != null) {
            findViewById<TextView>(R.id.txt_room_name).text = room.name
            findViewById<TextView>(R.id.txt_building_id).text = room.buildingId.toString()
            findViewById<TextView>(R.id.txt_room_current_temperature).text =
                room.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_room_target_temperature).text =
                room.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_room_id).text = room.id.toString()
        }
    }
}