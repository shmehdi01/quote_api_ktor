package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.models.entities.Users
import api.shmehdi.qouteapp.data.services.AuthService
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.utils.getSingleOrNull
import api.shmehdi.qouteapp.utils.toUser
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AuthRepository : AuthService {

    companion object {


    }


    override suspend fun register(request: RegisterRequest): User? {

        return dbQuery {
            val row = Users.insert {
                it[name] = request.name
                it[email] = request.email
                it[password] = request.password
                it[isActive] = true
            }
            val id = row.resultedValues?.single()?.get(Users.id) ?: -1

            return@dbQuery if (id != -1)
                Users.select { Users.id eq id }.single().let { toUser(it) }
            else null
        }
    }

    override suspend fun login(request: LoginRequest): User? {
        return dbQuery {
            val user = Users.select {
                Users.email eq request.email
            }.getSingleOrNull {
                toUser(it)
            }

            user?.let {
                if (it.password == request.password)
                    return@dbQuery it
            }

            return@dbQuery null

        }
    }
}