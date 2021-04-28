package com.project.hotel.domain.usersrepository

import com.project.hotel.domain.model.T1

interface T1Repository {
    suspend fun addT1(t1: T1);
    suspend fun getT1(): List<T1>?;
}