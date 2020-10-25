package xyz.pavelkorolev.randomuser.main

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.about.di.AboutFeatureDependencies
import xyz.pavelkorolev.randomuser.about.di.AboutFeatureModule
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.di.ImageLoaderModule
import xyz.pavelkorolev.randomuser.di.ImageLoaderScope
import xyz.pavelkorolev.randomuser.di.NavigationModule
import xyz.pavelkorolev.randomuser.di.NavigationScope
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureDependencies
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureModule
import xyz.pavelkorolev.randomuser.network.UserApiRepository
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.splash.di.SplashFeatureModule
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureModule

interface MainActivityDependencies {

    interface DepProvider {
        fun provideMainActivityDependencies(): MainActivityDependencies
    }

    fun app(): Application

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository

    fun userDatabaseUpdater(): UserDatabaseUpdater
}

@NavigationScope
@ImageLoaderScope
@Component(
    modules = [
        NavigationModule::class,
        ImageLoaderModule::class,
        ViewModelFactoryModule::class,
        SplashFeatureModule::class,
        UserListFeatureModule::class,
        GenerateUserFeatureModule::class,
        AboutFeatureModule::class
    ],
    dependencies = [
        MainActivityDependencies::class
    ]
)
interface MainActivityComponent :
    SplashFeatureDependencies,
    UserListFeatureDependencies,
    GenerateUserFeatureDependencies,
    AboutFeatureDependencies {

    fun navigatorHolder(): NavigatorHolder

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
