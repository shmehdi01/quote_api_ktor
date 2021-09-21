package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.data.services.UserService
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.data.models.entities.Users
import api.shmehdi.qouteapp.data.models.entities.validate
import api.shmehdi.qouteapp.utils.toUser
import org.jetbrains.exposed.sql.*
import java.lang.Exception

class UserRepository(private val userService: UserService) : BaseRepository {


     suspend fun getUsers() = safeCall {
         userService.getUsers()
     }

     suspend fun getUser(id: Int) = safeCall {
         userService.getUser(id)
     }

     suspend fun addUser(user: User) = safeCall {
         userService.addUser(user.validate())
     }

     suspend fun deleteUser(id: Int) = safeCall {
         userService.deleteUser(id)
     }

     suspend fun updateUser(id: Int, user: User) = safeCall {
         userService.updateUser(id, user)
     }

}