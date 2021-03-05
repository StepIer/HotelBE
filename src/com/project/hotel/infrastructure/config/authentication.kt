package com.project.hotel.infrastructure.config

import com.auth0.jwt.exceptions.TokenExpiredException
import com.project.hotel.infrastructure.model.Credentials
import com.project.hotel.infrastructure.model.SimpleJWT
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get
import sun.security.util.KeyUtil.validate
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

//        val signInUseCase: SignInUseCase = get()
//        val signUpUseCase: SignUpUseCase = get()
//        val signOutUseCase: SignOutUseCase = get()
//        val getUserByEmailUseCase: GetUserByEmailUseCase = get()

        post("/auth/login") {
            val post = call.receive<Credentials>()
            val token = signInUseCase.signIn(
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

        route(Routes.AUTH_SIGN_UP) {
            val post = call.receive<Credentials>()
            val userWithEmailAndPassword = User(email = post.email, passwordHash = post.password)
            val resultUser = signUpUseCase.signUp(userWithEmailAndPassword) {
                simpleJwt.sign(userWithEmailAndPassword.email)
            }
            call.respond(mapOf("token" to resultUser.token))
        }

        route(Routes.AUTH_SIGN_OUT) {
            val currentUser = call.getUserIdPrincipal()?.name?.let { email -> getUserByEmailUseCase.getUser(email) }
                ?: throw AuthException.InvalidToken()
            val newToken = signOutUseCase.signOut(currentUser.email) { simpleJwt.sign(currentUser.email) }
            call.respond(mapOf("token" to newToken))
        }
    }
}
