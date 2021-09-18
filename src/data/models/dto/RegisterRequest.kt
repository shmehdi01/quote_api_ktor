package api.shmehdi.qouteapp.data.models.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)