package xyz.pavelkorolev.randomuser.core.mapper

interface Mapper<T, R> {

    suspend fun map(data: T): R

    suspend fun reverseMap(data: R): T = throw NotImplementedError()
}
