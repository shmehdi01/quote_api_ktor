package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.repository.UserRepository
import api.shmehdi.qouteapp.data.services.UserService
import io.ktor.routing.*

fun Route.authRoute() {

    val userRepository: UserService = UserRepository()

    routeV1 {
        get("login") {

        }

        get("register") {

        }
    }
}