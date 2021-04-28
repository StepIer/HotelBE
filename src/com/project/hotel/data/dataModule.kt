package com.project.hotel.data

import com.project.hotel.data.repository.HerokuT1Repozitory
import com.project.hotel.data.repository.HerokuUserRepository
import com.project.hotel.domain.usersrepository.T1Repository
import com.project.hotel.domain.usersrepository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { HerokuUserRepository() }
    // single<RoomRepository> { HerokuRoomRepository() }
    single<T1Repository> { HerokuT1Repozitory() }
}