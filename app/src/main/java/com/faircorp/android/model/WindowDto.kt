package com.faircorp.android.model

enum class Status { OPEN, CLOSED }

data class WindowDto(
    val id: Long,
    val name: String,
    val room: RoomDto,
    val windowStatus: Status?,
    val roomName: String?
)