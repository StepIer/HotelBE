package com.project.hotel.data.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/**
create TABLE Users(
id serial primary key,
name varchar(255) not null,
surname varchar(255) not null,
token varchar(255),
password_hash varchar(255) not null
);
* */

object UsersTable: Table("Users") {

    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", length = 255)
    val surname: Column<String> = varchar("surname", length = 255)
    val token: Column<String?> = varchar("token", length = 255).nullable()
    val password_hash: Column<String> = varchar("password_hash", length = 255)

}

