package com.project.hotel.infrastructure

import com.project.hotel.data.DatabaseFactory
import com.project.hotel.data.dataModule
import com.project.hotel.domain.domainModule
import com.project.hotel.infrastructure.config.allRouting
import com.project.hotel.infrastructure.config.authentication
import com.project.hotel.infrastructure.config.defaultConfig
import com.project.hotel.infrastructure.config.serialization
import io.ktor.application.*
import io.ktor.util.*

import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()
    install(Koin) {
        logger(PrintLogger())
        modules(dataModule, infrastructureModule, domainModule)
    }
    defaultConfig()
    serialization()
    authentication()
    allRouting()
}


