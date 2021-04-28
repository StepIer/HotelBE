package com.project.hotel.data.repository

import com.project.hotel.data.dbQuery
import com.project.hotel.data.entity.asT1
import com.project.hotel.data.entity.asT1Entity
import com.project.hotel.data.entity.asUser
import com.project.hotel.data.tables.T1Table
import com.project.hotel.data.tables.T1Table.login
import com.project.hotel.data.tables.UsersTable
import com.project.hotel.domain.model.T1
import com.project.hotel.domain.model.User
import com.project.hotel.domain.usersrepository.T1Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class HerokuT1Repozitory : T1Repository {
    override suspend fun addT1(t1: T1): Unit =
        dbQuery {
            T1Table.insert {
                it[login] = t1.login
            }
        }


    override suspend fun getT1(): List<T1>? =

    //        dbQuery {
//            T1Table.select{
//                T1Table.login eq login
//            }.map {
//                it.asT1()
//            }.singleOrNull()
//        }
    dbQuery {
        T1Table.selectAll()
            .mapNotNull {
                it.asT1Entity().asT1()
            }
    }
}