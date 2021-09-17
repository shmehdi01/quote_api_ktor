package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.data.services.UserService
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.models.entities.Users
import api.shmehdi.qouteapp.utils.toUser
import org.jetbrains.exposed.sql.*
import java.lang.Exception

class UserRepository : UserService {


    override suspend fun getUsers(): List<User> = dbQuery { Users.selectAll().map { toUser(it) } }

    override suspend fun getUser(id: Int): User? =
        dbQuery {
            val query = Users.select {
                Users.id eq id
            }.limit(1)

            try {
                return@dbQuery query.single().let {
                    toUser(it)
                }
            }catch (e :Exception) {
                return@dbQuery null
            }


        }


    override suspend fun addUser(user: User) {
        dbQuery {
            Users.insert {
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password
                it[isActive] = true
            }
        }
    }

    override suspend fun deleteUser(id: Int) {
        dbQuery {
            Users.deleteWhere { Users.id eq id }
        }
    }

    override suspend fun updateUser(id: Int, user: User) {
        dbQuery {
            Users.update {
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password
            }
        }
    }

}