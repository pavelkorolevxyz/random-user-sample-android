package xyz.pavelkorolev.randomuser.database

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * Database providing service
 */
class DatabaseService(
    application: Application
) {

    private val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, application, "randomuser.db")
    private val database = Database(driver)

    /**
     * Returns database instance to work with
     */
    fun getDatabase(): Database = database
}
