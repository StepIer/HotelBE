package com.project.hotel.data.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object T1Table : Table("T1") {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val login: Column<String> = varchar("login", 100)
}
