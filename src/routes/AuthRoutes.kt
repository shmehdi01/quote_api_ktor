package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.dto.BaseResponse
import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.repository.AuthRepository
import api.shmehdi.qouteapp.data.services.AuthService
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authRoute() {

    val authRepository: AuthService = AuthRepository()

    routeV1 {
        post  ("login") {
            val request = call.receive<LoginRequest>()
            val user = authRepository.login(request)
            if (user != null)
                call.respond(BaseResponse.success(user, "Success"))
            else
                call.respond(BaseResponse.error("Incorrect user/password"))
        }

        post("register") {
            val request = call.receive<RegisterRequest>()
            val user = authRepository.register(request)
            call.respond(BaseResponse.success(it, "Success"))
        }
    }
}

fun Application.registerAuthRoute() {
    routing {
        authRoute()
    }
}