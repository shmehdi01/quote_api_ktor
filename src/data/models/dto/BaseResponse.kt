package api.shmehdi.qouteapp.data.models.dto

data class BaseResponse<T>(
    val status: Boolean,
    val message: String?,
    val data: T?
) {

    companion object {

        fun <T> success(data : T , message: String? = null) = BaseResponse(
            status = true,
            message = message,
            data = data
        )

        fun emptySuccess(message: String? = null) = BaseResponse(
            status = true,
            message = message,
            data = null
        )

        fun error(message: String) = BaseResponse(
            status = false,
            message = message,
            data = null
        )
    }
}
