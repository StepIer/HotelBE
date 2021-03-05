package com.project.hotel.data.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

/**
create TABLE Rooms (
id serial primary key,
number int not null,
occupied bool not null,
user_id int,
cost int not null,
FOREIGN KEY (user_id) REFERENCES Users (id)
);
**/

object RoomsTable: Table("Rooms"){
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val number: Column<Int> = integer("number")
    val occupied: Column<Boolean> = bool("occupied")
    val user_id: Column<Int?> = integer("user_id").nullable()
    val cost: Column<Int> = integer("cost")

}