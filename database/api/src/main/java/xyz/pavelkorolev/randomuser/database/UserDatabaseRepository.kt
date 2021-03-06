package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.core.model.Try
import xyz.pavelkorolev.randomuser.model.User

/**
 * Repository to work with users in database
 */
interface UserDatabaseRepository {

    /**
     * Returns all users from database
     */
    suspend fun selectUsers(): Try<List<User>>

    /**
     * Inserts new users into database
     *
     * @param users users to insert
     */
    suspend fun insertUsers(users: List<User>): Try<Unit>

    /**
     * Deletes user by its database id
     *
     * @param id user database identifier
     */
    suspend fun delete(id: Long): Try<Unit>
}
