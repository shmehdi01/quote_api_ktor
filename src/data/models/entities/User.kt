package api.shmehdi.qouteapp.data.models.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table


data class User(
    val id: Int,
    val name: String,
    val password: String,
    val email: String,
    val isActive: Boolean
)

object Users: Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
    val password: Column<String> = varchar("password", 100)
    val email: Column<String> = varchar("email", 100)
    val isActive: Column<Boolean> = bool("active")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(id)
}
