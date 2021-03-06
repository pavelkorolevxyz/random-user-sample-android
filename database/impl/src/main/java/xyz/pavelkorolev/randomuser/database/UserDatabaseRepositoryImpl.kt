package xyz.pavelkorolev.randomuser.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.core.model.runTryCatching
import xyz.pavelkorolev.randomuser.database.domain.UserDatabaseEntityMapper
import xyz.pavelkorolev.randomuser.model.User

/**
 * SQLDelight implementation of [UserDatabaseRepository]
 */
class UserDatabaseRepositoryImpl(
    databaseService: DatabaseService,
    private val dispatcher: CoroutineDispatcher,
    private val userMapper: UserDatabaseEntityMapper
) : UserDatabaseRepository {

    private val database = databaseService.getDatabase()

    override suspend fun selectUsers(): Try<List<User>> = runTryCatching {
        withContext(dispatcher) {
            val databaseUsers = database.userQueries.selectAll().executeAsList()
            databaseUsers.map { userMapper.map(it) }
        }
    }

    override suspend fun insertUsers(users: List<User>): Try<Unit> = runTryCatching {
        withContext(dispatcher) {
            val databaseUsers = users.map { userMapper.reverseMap(it) }
            database.userQueries.transaction {
                for (user in databaseUsers) {
                    database.userQueries.insert(
                        first_name = user.first_name,
                        last_name = user.last_name,
                        avatar_url = user.avatar_url
                    )
                }
            }
        }
    }

    override suspend fun delete(id: Long): Try<Unit> = runTryCatching {
        withContext(dispatcher) {
            database.userQueries.delete(id)
        }
    }
}
