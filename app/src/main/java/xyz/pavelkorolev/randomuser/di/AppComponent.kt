package xyz.pavelkorolev.randomuser.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
abstract class AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
