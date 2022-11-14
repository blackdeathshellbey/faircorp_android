package com.faircorp.android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.R.*
import com.faircorp.android.model.ApiServices
import com.faircorp.android.model.DefaultDataBase
import com.faircorp.android.model.HeaterAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface OnHeaterSelectedListener {
    fun onHeaterSelected(id: Long)
}

class HeaterActivity : MenuActivity(), OnHeaterSelectedListener {

    val heaterataBase = DefaultDataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recyclerHeaterView = findViewById<RecyclerView>(id.list_heater)
        val heaterAdapter = HeaterAdapter(this)
        recyclerHeaterView.layoutManager = LinearLayoutManager(this)
        recyclerHeaterView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerHeaterView.setHasFixedSize(true)
        recyclerHeaterView.adapter = heaterAdapter
        heaterAdapter.update(heaterataBase.findAllHeaters())
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().heatersApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        heaterAdapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on loading heaters $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onHeaterSelected(id: Long) {
        val intent = Intent(this, HeaterDetailActivity::class.java).putExtra(HEATER_NAME_PARAM, id)
        startActivity(intent)
    }

}