package api.shmehdi.qouteapp.database

import org.flywaydb.core.Flyway

object Migration {

    fun migrate() {
        Flyway.configure()
            .dataSource(DatabaseFactory.hikari())
            .load()
            .migrate()
    }

}