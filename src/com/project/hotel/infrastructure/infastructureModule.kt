package com.project.hotel.infrastructure

import com.project.hotel.data.repository.HerokuUserRepository
import com.project.hotel.domain.usecases.AddNewUserUseCase
import com.project.hotel.domain.usersrepository.RoomRepository
import com.project.hotel.domain.usersrepository.UserRepository
import com.project.hotel.infrastructure.model.SimpleJWT
import org.koin.dsl.module

val infrastructureModule = module {

    single { SimpleJWT("Bearer") }

}
