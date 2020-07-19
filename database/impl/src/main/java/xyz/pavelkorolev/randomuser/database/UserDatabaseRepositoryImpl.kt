package xyz.pavelkorolev.randomuser.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.pavelkorolev.randomuser.database.domain.UserDatabaseEntityMapper
import xyz.pavelkorolev.randomuser.model.User

class UserDatabaseRepositoryImpl(
    databaseService: DatabaseService,
    private val userMapper: UserDatabaseEntityMapper
) : UserDatabaseRepository {

    private val database = databaseService.getDatabase()

    override suspend fun insertUsers(users: List<User>) = withContext(Dispatchers.IO) {
        val databaseUsers = users.map { userMapper.reverseMap(it) }
        database.userQueries.transaction {
            for (user in databaseUsers) {
                database.userQueries.insert(
                    first_name = user.first_name,
                    last_name = user.last_name
                )
            }
        }
    }

    override suspend fun selectUsers(): List<User> = withContext(Dispatchers.IO) {
        val databaseUsers = database.userQueries.selectAll().executeAsList()
        databaseUsers.map { userMapper.map(it) }
    }
}
