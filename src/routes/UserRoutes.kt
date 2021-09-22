package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.models.dto.BaseResponse
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.repository.UserRepository
import api.shmehdi.qouteapp.data.services.UserService
import api.shmehdi.qouteapp.database.impl.UserDaoImpl
import api.shmehdi.qouteapp.utils.DELETED_SUCCESSFULLY_MESSAGE
import api.shmehdi.qouteapp.utils.UPDATED_SUCCESSFULLY_MESSAGE
import api.shmehdi.qouteapp.utils.so
import api.shmehdi.qouteapp.vo.Resource
import api.shmehdi.qouteapp.vo.errorResponse
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRoute() {

    val userRepository = UserRepository(userService = UserService(userDao = UserDaoImpl()))

    routeV1("/users") {
        get {
            when (val resource = userRepository.getUsers()) {
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))

            when (val resource = userRepository.getUser(id)) {
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        post {
            val user = call.receive<User>()
            when (val resource = userRepository.addUser(user)) {
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        put("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))
            val user = call.receive<User>()

            when (val resource = userRepository.updateUser(id, user)) {
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data, UPDATED_SUCCESSFULLY_MESSAGE))
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))

            when (val resource = userRepository.deleteUser(id)) {
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data, DELETED_SUCCESSFULLY_MESSAGE))
            }
        }
    }
}

fun Application.registerUserRoute() {
    routing {
        authenticate("auth-jwt") {
            userRoute()
        }
    }
}

