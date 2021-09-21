package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.errors.InvalidCredentialException
import api.shmehdi.qouteapp.errors.NotFoundException
import api.shmehdi.qouteapp.errors.UserAlreadyExist
import api.shmehdi.qouteapp.errors.ValidationError
import api.shmehdi.qouteapp.utils.ErrorConstants
import api.shmehdi.qouteapp.vo.Resource
import io.ktor.http.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

interface BaseRepository {

    suspend fun <T> safeCall(notFoundError: Resource.Error? = null, f: suspend () -> T?): Resource<T> {
        return try {
            val t = f.invoke()
            if (t != null)
                Resource.Success(t)
            else
                notFoundError ?: Resource.Error(HttpStatusCode.NotFound.value, "Not found")

        }
        catch (e: Exception) {
            when (e) {
                is ValidationError -> Resource.Error(HttpStatusCode.UnprocessableEntity.value, e.message ?: ErrorConstants.SOMETHING_WENT_WRONG)
                is ExposedSQLException -> Resource.Error(HttpStatusCode.UnprocessableEntity.value, e.cause?.message ?: e.message ?: ErrorConstants.ALREADY_EXIST)
                is UserAlreadyExist -> Resource.Error(200, e.message ?: "User already exist")
                is NotFoundException -> Resource.Error(e.code, e.message ?: "Not found")
                is InvalidCredentialException -> Resource.Error(200, e.message ?: ErrorConstants.SOMETHING_WENT_WRONG)
                else -> Resource.Error(HttpStatusCode.InternalServerError.value, e.message ?: ErrorConstants.SOMETHING_WENT_WRONG)
            }

        }
    }
}