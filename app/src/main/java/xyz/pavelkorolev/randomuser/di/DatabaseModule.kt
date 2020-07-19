package xyz.pavelkorolev.randomuser.di

import android.app.Application
import dagger.Module
import dagger.Provides
import xyz.pavelkorolev.randomuser.database.DatabaseService
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.domain.UserDatabaseEntityMapper
import javax.inject.Scope

@Scope
internal annotation class DatabaseScope

@Module(
    includes = [
        DatabaseMapperModule::class
    ]
)
internal object DatabaseModule {

    @DatabaseScope
    @Provides
    fun provideDatabaseService(
        application: Application
    ): DatabaseService = DatabaseService(application)

    @DatabaseScope
    @Provides
    fun provideUserDatabaseRepository(
        databaseService: DatabaseService,
        userMapper: UserDatabaseEntityMapper
    ): UserDatabaseRepository = UserDatabaseRepository(databaseService, userMapper)
}

@Module
internal object DatabaseMapperModule {

    @DatabaseScope
    @Provides
    fun provideUserMapper(): UserDatabaseEntityMapper = UserDatabaseEntityMapper()
}
