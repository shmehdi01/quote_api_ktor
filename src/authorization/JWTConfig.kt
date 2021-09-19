package api.shmehdi.qouteapp.authorization

import api.shmehdi.qouteapp.data.models.entities.User
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import java.util.*

class JWTConfig {

    companion object {
        private val config = HoconApplicationConfig(ConfigFactory.load())
        private val secret = config.property("jwt.secret").getString()
        private val issuer = config.property("jwt.issuer").getString()
        private val algorithm = Algorithm.HMAC256(secret)

        val verifier: JWTVerifier =JWT.require(algorithm).withIssuer(issuer).build()
    }

    fun createToken(user: User):String {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("email", user.email)
            .sign(algorithm)
    }

}