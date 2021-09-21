package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.authorization.hash
import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.models.dto.toUser
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.database.dao.UserDao
import api.shmehdi.qouteapp.errors.InvalidCredentialException
import api.shmehdi.qouteapp.errors.NotFoundException
import api.shmehdi.qouteapp.errors.UserAlreadyExist
import api.shmehdi.qouteapp.vo.Email
import api.shmehdi.qouteapp.vo.Id

class AuthService(private val userDao: UserDao) {

    @Throws(UserAlreadyExist::class)
    suspend fun register(request: RegisterRequest): User? {
        val id = userDao.addUser(request.toUser()) ?: -1
        return  if (id != -1) userDao.getUser(Id(id))
        else null
    }

     @Throws(NotFoundException::class, InvalidCredentialException::class)
     suspend fun login(request: LoginRequest): User {
         val user = userDao.getUser(Email(request.email)) ?: throw NotFoundException("User not found with ${request.email}")
         if (user.password == hash(request.password))
             return user
         else throw InvalidCredentialException("email/password incorrect")

    }
}