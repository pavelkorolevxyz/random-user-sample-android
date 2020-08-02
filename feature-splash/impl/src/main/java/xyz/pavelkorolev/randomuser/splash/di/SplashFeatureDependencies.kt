package xyz.pavelkorolev.randomuser.splash.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi

interface SplashFeatureDependencies {

    interface DepProvider {
        fun provideSplashFeatureDependencies(): SplashFeatureDependencies
    }

    fun router(): Router
    fun userListFeatureApi(): UserListFeatureApi
}
