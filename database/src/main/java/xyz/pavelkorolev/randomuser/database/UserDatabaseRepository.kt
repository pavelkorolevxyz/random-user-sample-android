package xyz.pavelkorolev.randomuser.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.pavelkorolev.randomuser.database.model.UserDatabaseEntity

class UserDatabaseRepository(
    databaseService: DatabaseService
) {

    private val database = databaseService.getDatabase()

    suspend fun insertUsers(users: List<UserDatabaseEntity>) = withContext(Dispatchers.IO) {
        database.userQueries.transaction {
            for (user in users) {
                database.userQueries.insert(
                    first_name = user.first_name,
                    last_name = user.last_name
                )
            }
        }
    }

    suspend fun selectUsers(): List<UserDatabaseEntity> = withContext(Dispatchers.IO) {
        database.userQueries.selectAll().executeAsList()
    }
}
