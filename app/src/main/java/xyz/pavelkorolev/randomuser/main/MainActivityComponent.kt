package xyz.pavelkorolev.randomuser.main

import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.di.NavigationModule
import xyz.pavelkorolev.randomuser.di.NavigationScope
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import xyz.pavelkorolev.randomuser.splash.api.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.impl.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.splash.impl.di.SplashFeatureModule
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureModule

interface MainActivityDependencies {

    interface DepProvider {
        fun provideMainActivityDependencies(): MainActivityDependencies
    }

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository
}

@NavigationScope
@Component(
    modules = [
        NavigationModule::class,
        ViewModelFactoryModule::class,
        SplashFeatureModule::class,
        UserListFeatureModule::class
    ],
    dependencies = [
        MainActivityDependencies::class
    ]
)
interface MainActivityComponent :
    SplashFeatureDependencies,
    UserListFeatureDependencies {

    fun navigatorHolder(): NavigatorHolder

    override fun router(): Router

    fun navigator(): Navigator

    fun splashFeatureApi(): SplashFeatureApi

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: FragmentActivity,
            dependencies: MainActivityDependencies
        ): MainActivityComponent
    }
}
