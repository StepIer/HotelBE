package com.project.hotel.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/*
*
* class UsersEntity {
    val id: Int = 0
    val name: String = " "
    val surname: String = " "
    val token: String? = null
    val password_hash: String = " "

}
 */


@Serializable
data class User(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = " ",
    @SerialName("surname") val surname: String = " ",
    @SerialName("token") val token: String? = null,
    @SerialName("password_hash") val password_hash: String = " "
)

fun User.asString(pretty: Boolean = false) = Json{
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(User.serializer(), this)

fun List<User>.asString(pretty: Boolean = false) = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    prettyPrint = pretty
}.encodeToString(ListSerializer(User.serializer()), this)