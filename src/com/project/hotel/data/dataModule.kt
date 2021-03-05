package com.project.hotel.data

import com.project.hotel.data.repository.HerokuUserRepository
import com.project.hotel.domain.usersrepository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { HerokuUserRepository() }
    // single<RoomRepository> { HerokuRoomRepository() }
}