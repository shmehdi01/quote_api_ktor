package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.models.entities.User

interface AuthService {

    suspend fun register(request: RegisterRequest): User?

    suspend fun login(request: LoginRequest): User?
}