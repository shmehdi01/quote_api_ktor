package api.shmehdi.qouteapp.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Category(
    val id: Int,
    val name: String,
    val isActive: Boolean
)


object Categories: Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val isActive: Column<String> = varchar("active", 100)
}