package com.faircorp.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val ROOM_NAME_PARAM = "com.faircorp.roomname.attribute"
const val HEATER_NAME_PARAM = "com.faircorp.heatername.attribute"
const val BUILDING_NAME_PARAM = "com.faircorp.buildingname.attribute"

class MainActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRoom(view: View) {
        val toastRoom = "Room Tab"
        Toast.makeText(this, "You Selected $toastRoom", Toast.LENGTH_LONG).show()
        val intent = Intent(this, RoomActivity::class.java).apply {
            putExtra(ROOM_NAME_PARAM, toastRoom)
        }
        startActivity(intent)
    }

    fun openWindow(view: View) {
        val toastWindow = "Window Tab"
        Toast.makeText(this, "You Selected $toastWindow", Toast.LENGTH_LONG).show()
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, toastWindow)
        }
        startActivity(intent)
    }

    fun openHeater(view: View) {
        val toastHeater = "Heater Tab"
        Toast.makeText(this, "You Selected $toastHeater", Toast.LENGTH_LONG).show()
        val intent = Intent(this, HeaterActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, toastHeater)
        }
        startActivity(intent)
    }

    fun openBuilding(view: View) {
        val toastRoom = "Building Tab"
        Toast.makeText(this, "You Selected $toastRoom", Toast.LENGTH_LONG).show()
        val intent = Intent(this, BuildingActivity::class.java).apply {
            putExtra(BUILDING_NAME_PARAM, toastRoom)
        }
        startActivity(intent)
    }

}