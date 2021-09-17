package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.dto.BaseResponse
import api.shmehdi.qouteapp.data.repository.UserRepository
import api.shmehdi.qouteapp.data.services.UserService
import api.shmehdi.qouteapp.models.User
import api.shmehdi.qouteapp.utils.so
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRoute() {

    val userRepository: UserService = UserRepository()

    routeV1("/users") {
        get {
            call.respond(BaseResponse.success(userRepository.getUsers()))
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))
            val user = userRepository.getUser(id)
            call.respond(((user != null) so BaseResponse.success(user)) ?: BaseResponse.error("No User Found"))
        }

        post {
            val user = call.receive<User>()
            userRepository.addUser(user)
            call.respond(BaseResponse.emptySuccess(message = "User stored correctly",))
        }

        put("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))
            val user = call.receive<User>()
            userRepository.updateUser(id, user)
            call.respond(BaseResponse.emptySuccess(message =  "User updated successfully",))
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("User Id not found"))
            userRepository.deleteUser(id)
            call.respond(BaseResponse.emptySuccess( message = "User delete successfully"))
        }
    }
}

fun Application.registerUserRoute() {
    routing {
        userRoute()
    }
}

