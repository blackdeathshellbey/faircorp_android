package com.faircorp.android

import android.os.Bundle
import android.widget.TextView
import com.faircorp.android.model.DefaultDataBase

class BuildingDetailActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val buildingId = this.intent.getLongExtra(BUILDING_NAME_PARAM, 3)
        val building = DefaultDataBase.findBuildingById(buildingId)
        if (building != null) {
            findViewById<TextView>(R.id.txt_buildings_id).text = building.id.toString()
            findViewById<TextView>(R.id.txt_building_name).text = building.name.toString()
            findViewById<TextView>(R.id.txt_building_address).text = building.adress.toString()
            findViewById<TextView>(R.id.txt_building_city).text = building.city.toString()
            findViewById<TextView>(R.id.txt_building_zip_code).text = building.zipCode.toString()
        }
    }
}
