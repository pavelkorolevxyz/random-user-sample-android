package xyz.pavelkorolev.randomuser.splash.impl.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi

interface SplashFeatureDependencies {

    interface DepProvider {
        fun provideSplashFragmentDependencies(): SplashFeatureDependencies
    }

    fun router(): Router
    fun userListFeatureApi(): UserListFeatureApi
}
