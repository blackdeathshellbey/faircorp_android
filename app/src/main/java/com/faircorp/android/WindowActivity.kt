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

interface OnWindowSelectedListener {
    fun onWindowSelected(id: Long)
}

class WindowActivity : MenuActivity(), OnWindowSelectedListener {

    val windowDataBase = DefaultDataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recyclerWindowView = findViewById<RecyclerView>(R.id.list_window)
        val windowAdapter = WindowAdapter(this)
        recyclerWindowView.layoutManager = LinearLayoutManager(this)
        recyclerWindowView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerWindowView.setHasFixedSize(true)
        recyclerWindowView.adapter = windowAdapter
        windowAdapter.update(windowDataBase.findAllWindows())
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        windowAdapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowDetailActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }

}