package com.project.hotel.domain.usecases

import com.project.hotel.domain.model.T1
import com.project.hotel.domain.usersrepository.T1Repository

class ShowT1ListUseCase(private val t1Repository: T1Repository) {
    suspend fun getT1(): List<T1> =
        t1Repository.getT1() ?: listOf()
}