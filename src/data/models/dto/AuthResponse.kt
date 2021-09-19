package api.shmehdi.qouteapp.data.models.dto

import api.shmehdi.qouteapp.data.models.entities.User

data class AuthResponse(
    val token: String,
    val user: User
)
