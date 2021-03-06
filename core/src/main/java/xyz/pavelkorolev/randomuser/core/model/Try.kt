package xyz.pavelkorolev.randomuser.core.model

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an arbitrary [Throwable] exception.
 */
sealed class Try<out T> {
    data class Success<T>(val value: T) : Try<T>()
    data class Failure<T>(val exception: Throwable) : Try<T>()

    /**
     * Returns the encapsulated value if this instance represents [Success] or `null`
     * if it is [Failure].
     */
    fun getOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }

    /**
     * Returns the encapsulated [Throwable] exception if this instance represents [Failure] or `null`
     * if it is [Success]
     */
    fun exceptionOrNull(): Throwable? = when (this) {
        is Success -> null
        is Failure -> exception
    }
}

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and encapsulating it as a failure.
 */
inline fun <R> runTryCatching(block: () -> R): Try<R> {
    return try {
        Try.Success(block())
    } catch (e: Throwable) {
        Try.Failure(e)
    }
}

/**
 * Returns the encapsulated value if this instance represents [Try.Success] or throws the encapsulated [Throwable] exception
 * if it is [Try.Failure].
 */
fun <T> Try<T>.getOrThrow(): T = when (this) {
    is Try.Success -> value
    is Try.Failure -> throw exception
}
