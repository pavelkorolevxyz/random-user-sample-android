package xyz.pavelkorolev.randomuser.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.main.MainActivityDependencies

@NetworkScope
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface AppComponent : MainActivityDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
