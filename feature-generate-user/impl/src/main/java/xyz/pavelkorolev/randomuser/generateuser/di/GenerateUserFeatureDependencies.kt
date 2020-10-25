package xyz.pavelkorolev.randomuser.generateuser.di

import com.github.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.database.UserDatabaseUpdater
import xyz.pavelkorolev.randomuser.network.UserApiRepository

interface GenerateUserFeatureDependencies {

    interface DepProvider {
        fun provideGenerateUserFeatureDependencies(): GenerateUserFeatureDependencies
    }

    fun router(): Router

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository

    fun userDatabaseUpdater(): UserDatabaseUpdater
}
