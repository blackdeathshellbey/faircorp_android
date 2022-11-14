package com.faircorp.android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.model.*
import com.faircorp.android.model.DefaultDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface OnBuildingSelectedListener {
    fun onBuildingSelected(id: Long)
}

class BuildingActivity : MenuActivity(), OnBuildingSelectedListener {

    val buildingDataBase = DefaultDataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recyclerBuildingView = findViewById<RecyclerView>(R.id.list_building)
        val buildingAdapter = BuildingAdapter(this)
        recyclerBuildingView.layoutManager = LinearLayoutManager(this)
        recyclerBuildingView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerBuildingView.setHasFixedSize(true)
        recyclerBuildingView.adapter = buildingAdapter
        buildingAdapter.update(buildingDataBase.findAllBuildings())
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().buildingsApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        buildingAdapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on loading buildings$it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onBuildingSelected(id: Long) {
        val intent = Intent(this, BuildingDetailActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }

}