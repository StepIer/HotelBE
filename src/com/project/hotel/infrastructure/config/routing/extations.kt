package com.project.hotel.infrastructure.config.routing

import io.ktor.application.*
import io.ktor.auth.*

fun ApplicationCall.getUserIdPrincipal(): UserIdPrincipal? {
    return principal()
}

fun ApplicationCall.getToken(): String? {
    return request.headers["Authorization"]?.split(" ")?.takeIf { it.size > 1 }?.run { this[1] }
}

fun ApplicationCall.getParameter(param: String): String? = parameters[param]

fun ApplicationCall.requireParameter(param: String): String =
    getParameter(param) ?: throw Exception(param)