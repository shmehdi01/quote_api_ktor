package api.shmehdi.qouteapp.database


import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object DatabaseFactory {

    private const val DEFAULT_DB = "mysql"

    fun init() = Database.connect(hikari())

    fun hikari(defaultDb: String = DEFAULT_DB): HikariDataSource {
        
        val appConfig = HoconApplicationConfig(ConfigFactory.load())
        val dbConfig = appConfig.config("database")
        val db = dbConfig.config(defaultDb)
        val hikariConfig = HikariConfig().apply {
            driverClassName = db.property("driverClassName").getString()
            jdbcUrl = db.property("jdbcUrl").getString()
            username = db.property("username").getString()
            password = db.property("password").getString()
        }

        return HikariDataSource(hikariConfig)
    }
}


suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }




