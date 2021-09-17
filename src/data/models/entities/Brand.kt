package api.shmehdi.qouteapp.data.models.entities

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Brand(
    val id: Int,
    val name: String,
    val photo: String
)


object Brands: Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val name: Column<String> = varchar("name", 100)
    val photo: Column<String> = varchar("photo", 100)
}