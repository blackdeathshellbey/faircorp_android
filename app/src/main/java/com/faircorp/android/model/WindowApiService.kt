package com.faircorp.android.model

import retrofit2.Call
import retrofit2.http.*


interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>
}