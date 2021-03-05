package com.project.hotel.infrastructure.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Credentials(
    @SerialName("name") val name: String,
    @SerialName("password") val password: String
)

