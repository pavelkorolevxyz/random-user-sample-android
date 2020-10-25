package xyz.pavelkorolev.randomuser.splash.di

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi

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
