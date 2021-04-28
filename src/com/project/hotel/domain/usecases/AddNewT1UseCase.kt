package com.project.hotel.domain.usecases

import com.project.hotel.domain.model.T1
import com.project.hotel.domain.usersrepository.T1Repository

class AddNewT1UseCase(private val t1Repository: T1Repository) {
    suspend fun addT1(t1: T1){
        t1Repository.addT1(t1)
    }
}