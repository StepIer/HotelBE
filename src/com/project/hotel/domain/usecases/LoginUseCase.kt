package com.project.hotel.domain.usecases

import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(private val userRepository: UserRepository) {
    suspend fun login(
        name: String,
        password: String,
        tokenChecker: (String) -> Boolean,
        tokenGenerator: (User) -> String
    ): String =
        withContext(Dispatchers.IO) {
            val user: User? = userRepository.getByName(name) //сделать метод достать юзера по имени, если нет то нуль
            if (user == null) {
                throw Exception("error login")
            } else if (password != user.password_hash) {
                throw Exception("error password")
            }
            return@withContext user.token?.takeIf { tokenChecker(it) } ?: run {
                val newToken = tokenGenerator(user)
                user.token = newToken
                userRepository.update(user) // обновить юзера в базе данных
                newToken
            }
        }
}
