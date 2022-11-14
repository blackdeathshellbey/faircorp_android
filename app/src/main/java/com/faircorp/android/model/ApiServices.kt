package com.faircorp.android.model

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory.*

const val API_USERNAME = "user"
const val API_PASSWORD = "password"
const val URL_LINK = "https://faircorp-client-for-android.cleverapps.io/api/"

class ApiServices {
    private val userInfo = Credentials.basic(API_USERNAME, API_PASSWORD)
    private val interceptor = Interceptor { chain ->
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", userInfo).build()
        chain.proceed(authenticatedRequest)
    }
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val userAccount = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(logging).build()
    val roomsApiService: RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create()).client(userAccount)
            .baseUrl(URL_LINK)
            .build()
            .create(RoomApiService::class.java)
    }
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(create())
            .client(userAccount)
            .baseUrl(URL_LINK)
            .build()
            .create(WindowApiService::class.java)
    }

    val buildingsApiService: BuildingApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create()).client(userAccount)
            .baseUrl(URL_LINK)
            .build()
            .create(BuildingApiService::class.java)
    }

    val heatersApiService: HeaterApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create()).client(userAccount)
            .baseUrl(URL_LINK)
            .build()
            .create(HeaterApiService::class.java)
    }
}

