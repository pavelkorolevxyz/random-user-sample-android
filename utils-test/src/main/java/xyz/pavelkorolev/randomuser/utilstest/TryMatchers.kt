package xyz.pavelkorolev.randomuser.utilstest

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should
import io.kotest.matchers.shouldNot
import xyz.pavelkorolev.randomuser.core.model.Try
import kotlin.reflect.KClass

fun <T> Try<T>.shouldBeSuccess(block: ((T?) -> Unit)? = null) {
    this should BeSuccess()
    block?.invoke(getOrNull())
}

fun <T> Try<T>.shouldNotBeSuccess() = this shouldNot BeSuccess()

infix fun <T> Try<T>.shouldBeSuccess(expected: T) = this should BeSuccess(expected)
infix fun <T> Try<T>.shouldNotBeSuccess(expected: T) = this shouldNot BeSuccess(expected)

fun Try<Any?>.shouldBeFailure(block: ((Throwable?) -> Unit)? = null) {
    this should BeFailure()
    block?.invoke(exceptionOrNull())
}

fun Try<Any?>.shouldNotBeFailure() = this shouldNot BeFailure()

inline fun <reified A : Throwable> Try<Any?>.shouldBeFailureOfType() = this should BeFailureOfType(A::class)
inline fun <reified A : Throwable> Try<Any?>.shouldNotBeFailureOfType() = this shouldNot BeFailureOfType(A::class)

class BeSuccess<T>(private val expected: T? = null) : Matcher<Try<T>> {

    override fun test(value: Try<T>): MatcherResult = when (value) {
        is Try.Success -> {
            when (expected) {
                null -> defaultResult(true)
                else -> MatcherResult(
                    value.value == expected,
                    "Try should be a Success($expected), but instead got Success(${value.value})",
                    "Try should not be a Success($expected)"
                )
            }
        }
        is Try.Failure -> defaultResult(false)
    }

    private fun defaultResult(passed: Boolean) = MatcherResult(
        passed,
        "Try should be a success",
        "Try should not be a success"
    )
}

class BeFailure : Matcher<Try<Any?>> {

    override fun test(value: Try<Any?>) = MatcherResult(
        value is Try.Failure,
        "Try should be a failure but was $value",
        "Try should not be a failure"
    )
}

class BeFailureOfType<A : Throwable>(private val clazz: KClass<A>) : Matcher<Try<Any?>> {

    override fun test(value: Try<Any?>): MatcherResult = when (value) {
        is Try.Success -> MatcherResult(
            false,
            "Try should be a failure but was $value",
            ""
        )
        is Try.Failure -> {
            when {
                clazz.isInstance(value.exception) -> MatcherResult(
                    true,
                    "Try should be a Failure($clazz)",
                    "Try should not be a Failure($clazz)"
                )
                else -> MatcherResult(
                    false,
                    "Try should be a Failure($clazz) but was Failure(${value.exception::class})",
                    ""
                )
            }
        }
    }
}
