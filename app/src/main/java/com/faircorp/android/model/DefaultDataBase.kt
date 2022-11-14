package com.faircorp.android.model


class DefaultDataBase {

    companion object {

        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room 210", 15.1, 19.0,1),
            RoomDto(2, "Room 224", 16.6, 18.3,1),
            RoomDto(3, "Room 220", 14.2, 19.0,1)
        )

        val WINDOWS: List<WindowDto> = listOf(
            WindowDto(1, "Entry Window", ROOMS[0], Status.CLOSED,"room1"),
            WindowDto(2, "Back Window", ROOMS[0], Status.CLOSED,"room1"),
            WindowDto(3, "Sliding door", ROOMS[1], Status.OPEN,"room6"),
            WindowDto(4, "Window 1", ROOMS[2], Status.CLOSED,"room2"),
            WindowDto(5, "Window 2", ROOMS[2], Status.CLOSED,"room2"),
        )
        val HEATER: List<HeaterDto> = listOf(
            HeaterDto(19.0,1,15.1,"Heater 1","Room 210",HeaterStatus.ON),
            HeaterDto(19.0,2,13.1,"Heater 2","Room 210",HeaterStatus.OFF)
        )
        val BUILDING: List<BuildingDto> = listOf(
            BuildingDto(1,"Ecole Des Mines","Rue 1","Saint Etienne", 42000),
            BuildingDto(2,"Ecole Des Mines, Space Furiel","Rue 17","Saint Etienne", 42000)
        )

        fun findWindowById(id: Long) = WINDOWS.firstOrNull { it.id == id}
        fun findRoomById(id: Long) = ROOMS.firstOrNull { it.id == id}
        fun findHeaterById(id: Long) = HEATER.firstOrNull { it.id == id}
        fun findBuildingById(id: Long) = BUILDING.firstOrNull { it.id == id}
    }

    fun findAllWindows() = WINDOWS.sortedBy { it.name }
    fun findAllRooms() = ROOMS.sortedBy { it.name }
    fun findAllHeaters() = HEATER.sortedBy { it.id }
    fun findAllBuildings() = BUILDING.sortedBy { it.id }

}