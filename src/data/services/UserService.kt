package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.database.dao.UserDao
import api.shmehdi.qouteapp.errors.NotFoundException
import api.shmehdi.qouteapp.errors.UserAlreadyExist
import api.shmehdi.qouteapp.vo.Email
import api.shmehdi.qouteapp.vo.Id

class UserService(private val userDao: UserDao) {

    @Throws(UserAlreadyExist::class)
    suspend fun addUser(user: User) {
        val isExist = isUserExist(user.email)
        if (isExist) throw UserAlreadyExist("User already exist with ${user.email}")
        userDao.addUser(user)
    }

    @Throws(NotFoundException::class)
    suspend fun updateUser(id: Int, user: User) {
        val isExist = isUserExist(id)
        if (!isExist)
            throw  NotFoundException("User not found with id $id")

        userDao.updateUser(Id(id), user)
    }

    suspend fun getUsers() = userDao.getUsers()

    @Throws(NotFoundException::class)
    suspend fun getUser(id: Int): User {
        return userDao.getUser(Id(id)) ?: throw  NotFoundException("User not found with id $id")
    }

    @Throws(NotFoundException::class)
    suspend fun getUser(email: String): User {
        return userDao.getUser(Email(email)) ?: throw  NotFoundException("User not found with email $email")
    }

    suspend fun deleteUser(id: Int) {
        val isExist = isUserExist(id)
        if (!isExist)
            throw  NotFoundException("User not found with id $id")
        userDao.deleteUser(Id(id))
    }

    suspend fun isUserExist(id: Int) = userDao.getUser(Id(id)) != null

    suspend fun isUserExist(email: String) = userDao.getUser(Email(email)) != null
}