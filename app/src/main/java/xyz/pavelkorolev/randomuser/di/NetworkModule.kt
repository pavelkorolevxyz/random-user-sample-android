package xyz.pavelkorolev.randomuser.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import xyz.pavelkorolev.randomuser.BuildConfig
import xyz.pavelkorolev.randomuser.network.HttpClientFactory
import xyz.pavelkorolev.randomuser.network.RandomUserApiService
import javax.inject.Scope

@Scope
annotation class NetworkScope

@Module
class NetworkModule {

    @NetworkScope
    @Provides
    fun provideHttpClient(): HttpClient = HttpClientFactory.create(BuildConfig.DEBUG)

    @NetworkScope
    @Provides
    fun provideApiService(client: HttpClient): RandomUserApiService = RandomUserApiService(client)
}
