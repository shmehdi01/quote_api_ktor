package api.shmehdi.qouteapp.utils

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow

infix fun <T> Boolean.so(data: T): T? = if (this) data else null

fun <T> Query.getSingleOrNull(f: (row: ResultRow) -> T): T? {

    return try {
        single().let {
            f.invoke(it)
        }
    } catch (_: Exception) {
        null
    }
}


