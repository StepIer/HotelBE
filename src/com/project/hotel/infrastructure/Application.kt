package com.project.hotel.infrastructure

import com.project.hotel.data.DatabaseFactory
import com.project.hotel.data.dbQuery
import com.project.hotel.data.entity.T1Entity
import com.project.hotel.data.entity.asT1
import com.project.hotel.data.entity.asT1Entity
import com.project.hotel.data.tables.T1Table
import com.project.hotel.data.tables.T1Table.login
import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.asString
import com.project.hotel.infrastructure.allRouting
import com.project.hotel.infrastructure.authentication
import com.project.hotel.infrastructure.defaultConfig
import com.project.hotel.infrastructure.serialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.auth.*
import io.ktor.util.*
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()
    defaultConfig()
    serialization()
    authentication()
    allRouting()
}


