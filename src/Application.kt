package api.shmehdi.qouteapp

import api.shmehdi.qouteapp.authorization.JWTConfig
import api.shmehdi.qouteapp.database.DatabaseFactory
import api.shmehdi.qouteapp.database.Migration
import api.shmehdi.qouteapp.routes.registerAuthRoute
import api.shmehdi.qouteapp.routes.registerQuoteRoute
import api.shmehdi.qouteapp.routes.registerUserRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.gson.*

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
    registerQuoteRoute()
}


