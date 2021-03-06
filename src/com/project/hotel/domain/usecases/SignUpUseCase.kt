package com.project.hotel.domain.usecases

import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.UserRepository

class SignUpUseCase(val userRepository: UserRepository) {
    suspend fun signUp(
        user: User,
        tokenGenerator: (User) -> String
    ): String {
        if (userRepository.getByName(user.name) != null) throw Exception("User already exist")
        return tokenGenerator(user).also {
            user.token = it
            userRepository.addUser(user)
        }


    }
}