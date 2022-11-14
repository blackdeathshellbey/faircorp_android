package com.faircorp.android.model

import retrofit2.Call
import retrofit2.http.GET

interface BuildingApiService {
    @GET("buildings")
    fun findAll(): Call<List<BuildingDto>>
}