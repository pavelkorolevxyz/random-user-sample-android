package xyz.pavelkorolev.randomuser.userlist.di

import com.github.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.ImageLoader
import xyz.pavelkorolev.randomuser.about.AboutFeatureApi
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.generateuser.GenerateUserFeatureApi
import xyz.pavelkorolev.randomuser.network.UserApiRepository

interface UserListFeatureDependencies {

    interface DepProvider {
        fun provideUserListFeatureDependencies(): UserListFeatureDependencies
    }

    fun generateUserFeature(): GenerateUserFeatureApi

    fun aboutFeature(): AboutFeatureApi

    fun router(): Router

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository

    fun userDatabaseUpdater(): UserDatabaseUpdater

    fun imageLoader(): ImageLoader
}
