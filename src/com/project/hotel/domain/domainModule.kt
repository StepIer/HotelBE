package com.project.hotel.domain

import com.project.hotel.domain.usecases.AddNewUserUseCase
import com.project.hotel.domain.usecases.LoginUseCase
import com.project.hotel.domain.usecases.SignUpUseCase
import org.koin.dsl.module

val domainModule = module {
    single { AddNewUserUseCase(get()) }
    single { LoginUseCase(get()) }
    single { SignUpUseCase(get()) }
}