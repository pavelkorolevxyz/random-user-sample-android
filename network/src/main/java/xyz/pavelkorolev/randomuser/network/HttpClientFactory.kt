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

object HttpClientFactory {

    fun create(
        isLoggingEnabled: Boolean = false
    ): HttpClient = HttpClient(OkHttp) {
        if (isLoggingEnabled) installLogging()
        installJson()
    }

    private fun HttpClientConfig<*>.installLogging() {
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger { // TODO inject
                override fun log(message: String) {
                    Log.d("API", message)
                }
            }
        }
    }

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
