package xyz.pavelkorolev.randomuser.generateuser.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.network.UserApiRepository

interface GenerateUserFeatureDependencies {

    interface DepProvider {
        fun provideGenerateUserFragmentDependencies(): GenerateUserFeatureDependencies
    }

    fun router(): Router

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository
}
