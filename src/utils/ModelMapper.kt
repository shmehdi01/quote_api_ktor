package api.shmehdi.qouteapp.utils

import api.shmehdi.qouteapp.models.User
import api.shmehdi.qouteapp.models.Users
import org.jetbrains.exposed.sql.ResultRow

fun toUser(row: ResultRow): User = User(
    id = row[Users.id],
    name = row[Users.name],
    email = row[Users.email],
    password = row[Users.password],
    isActive = row[Users.isActive],
)