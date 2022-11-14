package com.faircorp.android.model

data class HeaterDto(
    val targetTemperature: Double,
    val id: Long,
    val currentTemperature: Double,
    val name: String,
    val roomId: String,
    val heaterStatus: HeaterStatus
)
enum class HeaterStatus { ON, OFF }