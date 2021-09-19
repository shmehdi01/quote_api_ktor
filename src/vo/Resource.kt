package api.shmehdi.qouteapp.vo

sealed class Resource<out T> {
    class Success<T>(val data: T): Resource<T>()
    class Error(val errorCode: Int, val errorMessage: String): Resource<Nothing>()
}
