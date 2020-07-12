package xyz.pavelkorolev.randomuser.splash.impl.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi

@Component(
    dependencies = [
        SplashFeatureDependencies::class
    ]
)
interface SplashFeatureComponent {

    fun router(): Router
    fun userListFeatureApi(): UserListFeatureApi

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: SplashFeatureDependencies
        ): SplashFeatureComponent
    }
}
