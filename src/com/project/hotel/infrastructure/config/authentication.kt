package com.project.hotel.infrastructure.config

import com.auth0.jwt.exceptions.TokenExpiredException
import com.project.hotel.domain.model.User
import com.project.hotel.domain.usecases.LoginUseCase
import com.project.hotel.domain.usecases.SignUpUseCase
import com.project.hotel.infrastructure.model.Credentials
import com.project.hotel.infrastructure.model.SimpleJWT
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get

import java.time.Instant
import java.util.*

fun Application.authentication() {
    val simpleJWT: SimpleJWT = get()
    install(Authentication) {
        jwt {
            verifier(simpleJWT.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("name").asString())
            }
            authSchemes()
        }
    }
    routing {

        val loginUseCase: LoginUseCase = get()
        val signUpUseCase: SignUpUseCase = get()
//        val signOutUseCase: SignOutUseCase = get()
//        val getUserByEmailUseCase: GetUserByEmailUseCase = get()

        post("auth/login") {
            val post = call.receive<Credentials>()
            val token = loginUseCase.login(
                post.name,
                post.password,
                tokenChecker = {
                    try {
                        val decodedJWT = simpleJWT.verifier.verify(it)
                        decodedJWT.expiresAt?.let { date -> date > Date.from(Instant.now()) } ?: false
                    } catch (e: TokenExpiredException) {
                        false
                    }
                },
                tokenGenerator = { simpleJWT.sign(post.name) }
            )
            call.respond(mapOf("token" to token))
        }

        post("/auth/sign-up") {
            val post = call.receive<Credentials>()
            val userWithNameAndPassword = User(name = post.name, password_hash = post.password)
            val resultToken = signUpUseCase.signUp(userWithNameAndPassword) {
                simpleJWT.sign(it.name)
            }
            call.respond(mapOf("token" to resultToken))
        }

    }
}
