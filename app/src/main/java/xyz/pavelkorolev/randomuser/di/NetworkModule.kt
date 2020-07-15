package xyz.pavelkorolev.randomuser.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import xyz.pavelkorolev.randomuser.BuildConfig
import xyz.pavelkorolev.randomuser.logging.LoggingService
import xyz.pavelkorolev.randomuser.network.HttpClientFactory
import xyz.pavelkorolev.randomuser.network.RandomUserApiService
import javax.inject.Scope

@Scope
internal annotation class NetworkScope

@Module
internal object NetworkModule {

    @NetworkScope
    @Provides
    fun provideHttpClient(
        loggingService: LoggingService
    ): HttpClient = HttpClientFactory.create(
        loggingService,
        BuildConfig.DEBUG
    )

    @NetworkScope
    @Provides
    fun provideApiService(client: HttpClient): RandomUserApiService = RandomUserApiService(client)
}
