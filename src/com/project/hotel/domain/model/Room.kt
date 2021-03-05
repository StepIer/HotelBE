package com.project.hotel.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/*
* @Serializable
data class T1(
    @SerialName("id") val id: Int = 0,
    @SerialName("login") val login: String = "",
)

fun T1.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(T1.serializer(), this)

fun List<T1>.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(ListSerializer(T1.serializer()), this)
*
*
* class RoomsEntity {
    val id: Int = 0
    val number: Int = 0
    val occupied: Boolean = false
    val user_id: Int? = null
    val cost: Int = 0
}
* */

@Serializable
data class Room(
    @SerialName("id") val id: Int = 0,
    @SerialName("number") val number: Int = 0,
    @SerialName("occupied") val occupied: Boolean = false,
    @SerialName("user_id") val user_id: Int? = null,
    @SerialName("cost") val cost: Int = 0
)

fun Room.asString(pretty: Boolean = false) = Json{
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(Room.serializer(), this)

fun List<Room>.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(ListSerializer(Room.serializer()), this)