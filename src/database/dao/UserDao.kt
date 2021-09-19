package api.shmehdi.qouteapp.database.dao

import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.vo.Email
import api.shmehdi.qouteapp.vo.Id


interface UserDao {

    suspend fun getUsers(): List<User>

    suspend fun getUser(id: Id): User?

    suspend fun getUser(email: Email): User?

    suspend fun addUser(user: User): Int?

    suspend fun deleteUser(id: Id)

    suspend fun updateUser(id: Id, user: User)
}
