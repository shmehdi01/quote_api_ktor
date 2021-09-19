package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.data.models.dto.LoginRequest
import api.shmehdi.qouteapp.data.models.dto.RegisterRequest
import api.shmehdi.qouteapp.data.models.dto.toUser
import api.shmehdi.qouteapp.data.models.entities.User
import api.shmehdi.qouteapp.database.dao.UserDao
import api.shmehdi.qouteapp.vo.Email
import api.shmehdi.qouteapp.vo.Id

class AuthService(private val userDao: UserDao) {

    suspend fun register(request: RegisterRequest): User? {
        val id = userDao.addUser(request.toUser()) ?: -1
        return  if (id != -1) userDao.getUser(Id(id))
        else null
    }

     suspend fun login(request: LoginRequest): User? {
         val user = userDao.getUser(Email(request.email))
         if (user != null)
             if (user.password == request.password)
                 return user
         return null
    }
}