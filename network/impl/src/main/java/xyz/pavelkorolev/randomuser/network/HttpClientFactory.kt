package xyz.pavelkorolev.randomuser.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import xyz.pavelkorolev.randomuser.logging.LoggingService

/**
 * Factory used to create Http clients with given parameters
 */
object HttpClientFactory {

    /**
     * Creates [HttpClient] with logging using [loggingService].
     * [isLoggingEnabled] can be set to enable logging.
     */
    fun create(
        loggingService: LoggingService,
        isLoggingEnabled: Boolean = false
    ): HttpClient = HttpClient(OkHttp) {
        if (isLoggingEnabled) installLogging(loggingService)
        installJson()
    }

    /**
     * Adds logging to [HttpClient] configuration
     */
    private fun HttpClientConfig<*>.installLogging(loggingService: LoggingService) {
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    loggingService.log(message, Log.DEBUG)
                }
            }
        }
    }

    /**
     * Adds Json serializer to [HttpClient] configuration
     */
    private fun HttpClientConfig<*>.installJson() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json(
                    JsonConfiguration.Stable.copy(
                        ignoreUnknownKeys = true
                    )
                )
            )
        }
    }
}
