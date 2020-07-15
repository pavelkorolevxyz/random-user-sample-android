package xyz.pavelkorolev.randomuser.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import xyz.pavelkorolev.randomuser.logging.LoggingService
import xyz.pavelkorolev.randomuser.logging.LoggingServiceImpl
import xyz.pavelkorolev.randomuser.startup.StartupService
import javax.inject.Scope

@Scope
internal annotation class LoggingScope

@Module(
    includes = [
        LoggingImplModule::class
    ]
)
internal abstract class LoggingModule {

    @LoggingScope
    @Binds
    abstract fun bindLoggingService(impl: LoggingServiceImpl): LoggingService

    @LoggingScope
    @Binds
    @IntoSet
    abstract fun bindStartupService(impl: LoggingServiceImpl): StartupService
}

@Module
internal object LoggingImplModule {

    @LoggingScope
    @Provides
    fun provideLoggingServiceImpl(): LoggingServiceImpl = LoggingServiceImpl()
}
