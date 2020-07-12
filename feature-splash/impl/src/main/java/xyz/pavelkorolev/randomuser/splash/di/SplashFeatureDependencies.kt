package xyz.pavelkorolev.randomuser.splash.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi

interface SplashFeatureDependencies {

    interface DepProvider {
        fun provideSplashFragmentDependencies(): SplashFeatureDependencies
    }

    fun router(): Router
    fun userListFeatureApi(): UserListFeatureApi
}
