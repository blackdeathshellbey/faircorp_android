package com.faircorp.android.model

import retrofit2.Call
import retrofit2.http.*

interface HeaterApiService {
    @GET("heaters")
    fun findAll(): Call<List<HeaterDto>>


}