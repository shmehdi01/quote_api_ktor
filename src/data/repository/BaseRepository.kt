package api.shmehdi.qouteapp.data.repository

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
                else -> Resource.Error(HttpStatusCode.BadRequest.value, e.message ?: ErrorConstants.SOMETHING_WENT_WRONG)
            }

        }
    }
}