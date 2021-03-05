package com.project.hotel.data.entity


import com.project.hotel.data.tables.T1Table
import com.project.hotel.domain.model.T1
import org.jetbrains.exposed.sql.ResultRow


data class T1Entity(
    val id: Int = 0,
    val login: String = "",
)


fun T1Entity.asT1() = T1(id, login)
fun T1.asT1Entity() = T1Entity(id, login)


fun ResultRow.asT1Entity() = T1Entity(
    id = get(T1Table.id),
    login = get(T1Table.login)
)

fun ResultRow.asT1() = asT1Entity().asT1()

