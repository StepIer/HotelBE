package com.project.hotel.domain.usecases

import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.UserRepository

class AddNewUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun addUser(user: User) {
        userRepository.addUser(user)
    }
}