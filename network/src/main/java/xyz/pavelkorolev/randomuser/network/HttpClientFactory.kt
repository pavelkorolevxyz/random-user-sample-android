package xyz.pavelkorolev.randomuser.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object HttpClientFactory {

    fun create(): HttpClient = HttpClient(OkHttp) {
        if (BuildConfig.DEBUG) installLogging()
        installJson()
    }

    private fun HttpClientConfig<OkHttpConfig>.installLogging() {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("API", message)
                }
            }
        }
    }

    private fun HttpClientConfig<OkHttpConfig>.installJson() {
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
