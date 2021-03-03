package com.project.hotel.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer


@Serializable
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