package api.shmehdi.qouteapp.data.models.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Quote(
    val id: Int,
    val quote: String,
    val author: String,
    val authorId: String,
    val userId: Int,
    val isActive: Boolean
)

fun Quote.validate(): Quote = this

object Quotes: Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val quote: Column<String> = varchar("name", 100)
    val author: Column<String> = varchar("author", 100)
    val authorId: Column<String> = varchar("authorId", 100)
    val userId: Column<Int> = integer("userId")
    val isActive: Column<Boolean> = bool("active")
}