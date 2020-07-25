package xyz.pavelkorolev.randomuser.generateuser.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import javax.inject.Inject

class GenerateUsersUseCase @Inject constructor(
    private val userApiRepository: UserApiRepository,
    private val userDatabaseRepository: UserDatabaseRepository,
    private val userDatabaseUpdater: UserDatabaseUpdater
) {

    suspend operator fun invoke(count: Int): Result<Unit> = runCatching {
        val users = userApiRepository.getUsers(count).getOrThrow()
        userDatabaseRepository.insertUsers(users).getOrThrow()
        userDatabaseUpdater.requestUpdate(Unit)
    }
}
