package xyz.pavelkorolev.randomuser.main

import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.di.NavigationModule
import xyz.pavelkorolev.randomuser.di.NavigationScope
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.splash.di.SplashFeatureModule
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureModule

@NavigationScope
@Component(
    modules = [
        NavigationModule::class,
        SplashFeatureModule::class,
        UserListFeatureModule::class
    ]
)
interface MainActivityComponent : SplashFeatureDependencies {

    fun navigatorHolder(): NavigatorHolder
    override fun router(): Router

    fun navigator(): Navigator

    fun splashFeatureApi(): SplashFeatureApi

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: FragmentActivity
        ): MainActivityComponent
    }
}
