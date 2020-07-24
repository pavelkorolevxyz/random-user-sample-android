package xyz.pavelkorolev.randomuser.userlist.domain

import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userDatabaseRepository: UserDatabaseRepository
) {

    suspend operator fun invoke(id: Long): Result<Unit> {
        return userDatabaseRepository.delete(id)
    }
}