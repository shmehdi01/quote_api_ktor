package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.models.dto.validate
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.services.AuthService
import api.shmehdi.qouteapp.vo.Resource
import io.ktor.http.*

class AuthRepository(private val authService: AuthService): BaseRepository {

    suspend fun register(request: RegisterRequest): Resource<User> {
        return safeCall {
            authService.register(request.validate())
        }
    }

    suspend fun login(request: LoginRequest): Resource<User> {
        return safeCall(notFoundError = Resource.Error(HttpStatusCode.OK.value, "Username/password not found")) {
            authService.login(request.validate())
        }
    }
}
