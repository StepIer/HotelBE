package com.project.hotel.infrastructure.config.routing

import com.project.hotel.data.dbQuery
import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.User
import com.project.hotel.domain.usecases.AddNewUserUseCase
import io.ktor.application.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.insert
import org.koin.ktor.ext.get

fun Routing.userRoutings() {
    val addNewUserUseCase: AddNewUserUseCase = get()
    get("/add-Users") {
        val name = call.requireParameter("name")
        val surname = call.requireParameter("surname")
        val password_hash = call.requireParameter("password_hash")
        addNewUserUseCase.addUser(User(0, name, surname, password_hash))
        //dfvgbh
    }
}