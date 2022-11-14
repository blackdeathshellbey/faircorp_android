package com.faircorp.android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.android.model.ApiServices
import com.faircorp.android.model.DefaultDataBase
import com.faircorp.android.model.RoomAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface OnRoomSelectedListener {
    fun onRoomSelected(id: Long)
}

class RoomActivity : MenuActivity(), OnRoomSelectedListener {

    val roomDataBase = DefaultDataBase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val recyclerRoomView = findViewById<RecyclerView>(R.id.list_room)
        val windowAdapter = RoomAdapter(this)
        recyclerRoomView.layoutManager = LinearLayoutManager(this)
        recyclerRoomView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerRoomView.setHasFixedSize(true)
        recyclerRoomView.adapter = windowAdapter
        windowAdapter.update(roomDataBase.findAllRooms())
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        windowAdapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on loading rooms $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onRoomSelected(id: Long) {
        val intent = Intent(this, RoomDetailActivity::class.java).putExtra(ROOM_NAME_PARAM, id)
        startActivity(intent)
    }
}