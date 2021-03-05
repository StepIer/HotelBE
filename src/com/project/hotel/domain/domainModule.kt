package com.project.hotel.domain

import com.project.hotel.domain.usecases.AddNewUserUseCase
import org.koin.dsl.module

val domainModule = module {
    single { AddNewUserUseCase(get()) }
}