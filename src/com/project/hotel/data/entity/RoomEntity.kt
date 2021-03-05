package com.project.hotel.data.entity

import com.project.hotel.data.tables.RoomsTable
import com.project.hotel.domain.model.Room
import org.jetbrains.exposed.sql.ResultRow

/**
 * object RoomsTable: Table("Rooms"){
val id: Column<Int> = integer("id").autoIncrement().primaryKey()
val number: Column<Int> = integer("number")
val occupied: Column<Boolean> = bool("occupied")
val user_id: Column<Int?> = integer("user_id").nullable()
val cost: Column<Int> = integer("cost")

}
 */


data class RoomEntity (
    val id: Int = 0,
    val number: Int = 0,
    val occupied: Boolean = false,
    val user_id: Int? = null,
    val cost: Int = 0
)

fun RoomEntity.asRooms() = Room(id, number, occupied, user_id, cost)
fun Room.asRoomsEntity() = RoomEntity(id, number, occupied, user_id, cost)

fun ResultRow.asRoomsEntity() = RoomEntity(
    id = get(RoomsTable.id),
    number = get(RoomsTable.number),
    occupied = get(RoomsTable.occupied),
    user_id = get(RoomsTable.user_id),
    cost = get(RoomsTable.cost)
)


fun ResultRow.asRooms() = asT1Entity().asT1()
/*
fun T1Entity.asT1() = T1(id, login)
fun T1.asT1Entity() = T1Entity(id, login)


fun ResultRow.asT1Entity() = T1Entity(
    id = get(T1Table.id),
    login = get(T1Table.login)
)

fun ResultRow.asT1() = asT1Entity().asT1()

*/