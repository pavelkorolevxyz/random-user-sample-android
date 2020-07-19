package xyz.pavelkorolev.randomuser.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import xyz.pavelkorolev.randomuser.BuildConfig
import xyz.pavelkorolev.randomuser.logging.LoggingService
import xyz.pavelkorolev.randomuser.network.HttpClientFactory
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import xyz.pavelkorolev.randomuser.network.UserApiRepositoryImpl
import xyz.pavelkorolev.randomuser.network.domain.UserNetworkEntityMapper
import javax.inject.Scope

@Scope
internal annotation class NetworkScope

@Module(
    includes = [
        NetworkMapperModule::class
    ]
)
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
    fun provideUserApiRepository(
        client: HttpClient,
        userMapper: UserNetworkEntityMapper
    ): UserApiRepository = UserApiRepositoryImpl(client, userMapper)
}

@Module
internal object NetworkMapperModule {

    @NetworkScope
    @Provides
    fun provideUserMapper(): UserNetworkEntityMapper = UserNetworkEntityMapper()
}
