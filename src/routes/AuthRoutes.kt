package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.dto.BaseResponse
import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.repository.AuthRepository
import api.shmehdi.qouteapp.data.services.AuthService
import api.shmehdi.qouteapp.database.impl.UserDaoImpl
import api.shmehdi.qouteapp.vo.Resource
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authRoute() {

    val authRepository = AuthRepository(AuthService(userDao = UserDaoImpl()))

    routeV1 {
        post  ("login") {
            val request = call.receive<LoginRequest>()

            when (val resource = authRepository.login(request)) {
                is Resource.Error -> call.respond(HttpStatusCode.fromValue(resource.errorCode), BaseResponse.error(resource.errorMessage))
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        post("register") {
            val request = call.receive<RegisterRequest>()
//            val user = authRepository.register(request)
//            call.respond(BaseResponse.success(it, "Success"))
            when (val resource = authRepository.register(request)) {
                is Resource.Error -> call.respond(HttpStatusCode.fromValue(resource.errorCode), BaseResponse.error(resource.errorMessage))
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }
    }
}

fun Application.registerAuthRoute() {

    routing {
        authRoute()
    }
}