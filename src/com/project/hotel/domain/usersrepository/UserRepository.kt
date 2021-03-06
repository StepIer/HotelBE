package com.project.hotel.domain.usersrepository

import com.project.hotel.domain.model.User

interface UserRepository {
    suspend fun addUser(user: User)
    suspend fun getByName(name: String): User?
    suspend fun update(user: User)

}