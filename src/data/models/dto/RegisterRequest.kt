package api.shmehdi.qouteapp.data.models.dto

import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.errors.ValidationError

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Throws(ValidationError::class)
fun RegisterRequest.validate(): RegisterRequest {
    var message = ""
    when {
        name.isEmpty() -> message += "Name is empty"
        email.isBlank() -> message += "Email is empty"
        password.isEmpty() -> message += "Password is empty"
    }

    if (message.isNotEmpty()) throw ValidationError(message)
    return this
}

fun RegisterRequest.toUser() = User(
    id = -1,
    name = name,
    email = email,
    password = password,
    isActive = true
)