package xyz.pavelkorolev.randomuser.database

import xyz.pavelkorolev.randomuser.model.User

/**
 * Repository to work with users in database
 */
interface UserDatabaseRepository {

    /**
     * Returns all users from database
     */
    suspend fun selectUsers(): Result<List<User>>

    /**
     * Inserts new users into database
     *
     * @param users users to insert
     */
    suspend fun insertUsers(users: List<User>): Result<Unit>

    /**
     * Deletes user by its database id
     *
     * @param id user database identifier
     */
    suspend fun delete(id: Long): Result<Unit>
}
