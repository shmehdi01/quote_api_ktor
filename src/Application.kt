package api.shmehdi.qouteapp

import api.shmehdi.qouteapp.authorization.JWTConfig
import api.shmehdi.qouteapp.database.DatabaseFactory
import api.shmehdi.qouteapp.database.Migration
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.data.models.entities.Brand
import api.shmehdi.qouteapp.data.models.entities.Brands
import api.shmehdi.qouteapp.data.repository.UserRepository
import api.shmehdi.qouteapp.routes.registerAuthRoute
import api.shmehdi.qouteapp.routes.registerUserRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JWTConfig.verifier)
            validate { credential ->
                if (credential.payload.getClaim("email").asString() != "")
                    JWTPrincipal(credential.payload)
                else null
            }
        }
    }

    install(ContentNegotiation) {
        gson {}
    }

    DatabaseFactory.init()
    Migration.migrate()

    registerAuthRoute()
    registerUserRoute()
}


