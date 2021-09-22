package api.shmehdi.qouteapp.database.impl

import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.models.entities.Users
import api.shmehdi.qouteapp.database.dao.UserDao
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.utils.getSingleOrNull
import api.shmehdi.qouteapp.utils.isValid
import api.shmehdi.qouteapp.utils.toUser
import api.shmehdi.qouteapp.vo.Email
import api.shmehdi.qouteapp.vo.Id
import org.jetbrains.exposed.sql.*

class UserDaoImpl : UserDao {

    override suspend fun getUsers(): List<User> = dbQuery { Users.selectAll().map { toUser(it) } }

    override suspend fun getUser(id: Id): User? =
        dbQuery {
            Users.select {
                Users.id eq id.id
            }.getSingleOrNull {
                toUser(it)
            }
        }

    override suspend fun getUser(email: Email): User? {
        return dbQuery {
            return@dbQuery Users.select {
                Users.email eq email.email
            }.getSingleOrNull {
                toUser(it)
            }
        }
    }


    override suspend fun addUser(user: User): Int? =
        dbQuery {
          return@dbQuery Users.insert {
                it[name] = user.name
                it[email] = user.email
                it[password] = user.password
                it[isActive] = true
            }.resultedValues?.single()?.get(Users.id)
        }


    override suspend fun deleteUser(id: Id) {
        dbQuery {
            Users.deleteWhere { Users.id eq id.id }
        }
    }

    override suspend fun updateUser(id: Id, user: User) {
        dbQuery {
            Users.update({Users.id eq id.id}) {
                if(user.name.isValid())
                    it[name] = user.name
                if (user.email.isValid())
                    it[email] = user.email
                if(user.password.isValid())
                    it[password] = user.password
            }
        }
    }

}