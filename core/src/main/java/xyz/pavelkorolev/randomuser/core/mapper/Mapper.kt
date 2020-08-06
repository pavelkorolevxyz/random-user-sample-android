package xyz.pavelkorolev.randomuser.core.mapper

/**
 * Base Mapper interface for types [T] and [R]
 */
interface Mapper<T, R> {

    /**
     * Maps given [T] to [R]
     */
    suspend fun map(data: T): R

    /**
     * Maps given [R] to [T]
     *
     * @throws NotImplementedError by default. Implementation is optional.
     */
    suspend fun reverseMap(data: R): T = throw NotImplementedError()
}
