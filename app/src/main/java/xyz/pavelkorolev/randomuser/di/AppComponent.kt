package xyz.pavelkorolev.randomuser.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.main.MainActivityDependencies
import xyz.pavelkorolev.randomuser.startup.StartupService

@LoggingScope
@NetworkScope
@DatabaseScope
@Component(
    modules = [
        LoggingModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : MainActivityDependencies {

    fun startupServices(): Set<StartupService>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
