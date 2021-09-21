package api.shmehdi.qouteapp.vo

import api.shmehdi.qouteapp.data.models.dto.BaseResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

sealed class Resource<out T> {
    class Success<T>(val data: T): Resource<T>()
    class Error(val errorCode: Int, val errorMessage: String): Resource<Nothing>()
}

suspend fun Resource.Error.errorResponse(call: ApplicationCall) {
    call.respond(
        HttpStatusCode.fromValue(errorCode),
        BaseResponse.error(errorMessage)
    )
}
