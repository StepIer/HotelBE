package com.project.hotel.infrastructure.config

import com.project.hotel.data.dbQuery
import com.project.hotel.data.entity.asT1
import com.project.hotel.data.entity.asT1Entity
import com.project.hotel.data.tables.T1Table
import com.project.hotel.domain.model.T1
import com.project.hotel.domain.model.asString
import com.project.hotel.domain.usecases.AddNewT1UseCase
import com.project.hotel.domain.usecases.ShowT1ListUseCase
import com.project.hotel.infrastructure.config.routing.userRoutings
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.ul
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.koin.ktor.ext.get

fun Application.allRouting() {
    val addNewT1UseCase: AddNewT1UseCase = get()
    val showT1ListUseCase: ShowT1ListUseCase = get()

    routing {
        userRoutings()
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            println("get(\"/\")")
        }

        post("/add-t1") {
            println("post(\"/add-t1\")")
            val t1 = call.receive<T1>()
            addNewT1UseCase.addT1(t1)
            call.respondText("OK!", contentType = ContentType.Text.Plain)
        }

        get("/get-t1"){
            val t1List = showT1ListUseCase.getT1()
            call.respondText(t1List.asString())
            println("get(\"/get-t1\")")
        }


        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }

        get("/t1-list") {
            val t1List = dbQuery {
                T1Table.selectAll()
                    .mapNotNull {
                        it.asT1Entity().asT1()
                    }
            }
            call.respondText(t1List.asString())
        }

        get("/add-t1") {
            val login1 = call.parameters["login"] ?: throw Exception("null param")
            dbQuery {
                T1Table.insert {
                    it[login] = login1
                } get T1Table.id
            }
        }

        authenticate {
            get("/protected/route/basic") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.respondText("Hello ${principal.name}")
            }
        }

        //insert row Users
        /*class Users {
        val id: Int = 0
        val name: String = " "
        val surname: String = " "
        val token: String? = null
        val password_hash: String = " "

        }       */

    }

}