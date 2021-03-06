package com.project.hotel.data.repository

import com.project.hotel.data.dbQuery
import com.project.hotel.data.entity.asUser
import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class HerokuUserRepository : UserRepository {
    override suspend fun addUser(user: User): Unit = withContext(Dispatchers.IO) {

        dbQuery {
            UsersTable.insert {
                it[name] = user.name
                it[password_hash] = user.password_hash
                it[surname] = user.surname
            }
        }
    }

    override suspend fun getByName(name: String): User? =
        dbQuery {
            UsersTable.select {
                UsersTable.name eq name

            }.map {
                it.asUser()
            }.singleOrNull()
        }


    override suspend fun update(user: User): Unit = withContext(Dispatchers.IO){
        dbQuery {
            UsersTable.update {
                it[name] = user.name
                it[surname] = user.surname
                it[password_hash] = user.password_hash
                it[token] = user.token
            }
        }
    }


}