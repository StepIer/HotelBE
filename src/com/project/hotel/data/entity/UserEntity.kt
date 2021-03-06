package com.project.hotel.data.entity

import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.User
import org.jetbrains.exposed.sql.ResultRow


/**
object UsersTable: Table("Users") {

val id: Column<Int> = integer("id").autoIncrement().primaryKey()
val name: Column<String> = varchar("name", length = 255)
val surname: Column<String> = varchar("surname", length = 255)
val token: Column<String?> = varchar("token", length = 255).nullable()
val password_hash: Column<String> = varchar("password_hash", length = 255)

}
 */


data class UserEntity (
    val id: Int = 0,
    val name: String = " ",
    val surname: String = " ",
    val token: String? = null,
    val password_hash: String = " "
)

fun UserEntity.asUser() = User(id, name, surname, token, password_hash)
fun User.asUsersEntity() = UserEntity(id, name, surname, token, password_hash)

fun ResultRow.asUserEntyti() = UserEntity(
    id = get(UsersTable.id),
    name = get(UsersTable.name),
    surname = get(UsersTable.surname),
    token = get(UsersTable.token),
    password_hash = get(UsersTable.password_hash)
)
fun ResultRow.asUser() = asUserEntyti().asUser()
/*
fun T1Entity.asT1() = T1(id, login)
fun T1.asT1Entity() = T1Entity(id, login)


fun ResultRow.asT1Entity() = T1Entity(
    id = get(T1Table.id),
    login = get(T1Table.login)
)

fun ResultRow.asT1() = asT1Entity().asT1()

*/
