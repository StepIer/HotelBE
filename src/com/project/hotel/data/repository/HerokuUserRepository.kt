package com.project.hotel.data.repository

import com.project.hotel.data.dbQuery
import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.insert

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

}