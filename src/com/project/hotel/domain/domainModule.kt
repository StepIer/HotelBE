package com.project.hotel.domain

import com.project.hotel.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    single { AddNewUserUseCase(get()) }
    single { LoginUseCase(get()) }
    single { SignUpUseCase(get()) }
    single { AddNewT1UseCase(get()) }
    single { ShowT1ListUseCase(get()) }
}
