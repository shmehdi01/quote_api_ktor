package api.shmehdi.qouteapp

import api.shmehdi.qouteapp.database.DatabaseFactory
import api.shmehdi.qouteapp.database.Migration
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.models.Brand
import api.shmehdi.qouteapp.models.Brands
import api.shmehdi.qouteapp.models.Users
import api.shmehdi.qouteapp.routes.registerUserRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.ul
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {}

    install(ContentNegotiation) {
        gson {}
    }

    DatabaseFactory.init()
    Migration.migrate()

    registerUserRoute()
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/brand") {


            call.respond(BrandService().getBrands())


        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }



        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}


class  BrandService {
    suspend fun getBrands(): List<Brand> = dbQuery {
        Brands.selectAll().map {
            toBrand(it) }
    }
}

fun toBrand(row: ResultRow): Brand {
    return Brand(
        id = row[Brands.id],
        name = row[Brands.name],
        photo = row[Brands.photo]
    )
}
