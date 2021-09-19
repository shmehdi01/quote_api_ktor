package api.shmehdi.qouteapp.data.models.dto

import api.shmehdi.qouteapp.errors.ValidationError

data class LoginRequest(
    val email: String,
    val password: String
)


@Throws(ValidationError::class)
fun LoginRequest.validate(): LoginRequest {
    var message = ""
    when {
        email.isBlank() -> message = "Email is empty"
        password.isEmpty() -> message = "Password is empty"
    }

    if (message.isNotEmpty()) throw ValidationError(message)
    return this
}