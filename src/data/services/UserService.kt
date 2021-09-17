package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.models.User
import java.util.*


interface UserService {

    suspend fun getUsers(): List<User>

    suspend fun getUser(id: Int): User?

    suspend fun addUser(user: User)

    suspend fun deleteUser(id: Int)

    suspend fun updateUser(id: Int, user: User)
}