package api.shmehdi.qouteapp.utils

infix fun <T> Boolean.so(data: T) : T? = if (this) data else null


