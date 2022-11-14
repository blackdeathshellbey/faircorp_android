package com.faircorp.android.model

import retrofit2.Call
import retrofit2.http.*

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

}