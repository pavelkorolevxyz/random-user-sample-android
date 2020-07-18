package xyz.pavelkorolev.randomuser.userlist.impl.di

import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.database.UserDatabaseRepository
import xyz.pavelkorolev.randomuser.network.UserApiRepository

interface UserListFeatureDependencies {

    interface DepProvider {
        fun provideUserListFragmentDependencies(): UserListFeatureDependencies
    }

    fun router(): Router

    fun userApiRepository(): UserApiRepository

    fun userDatabaseRepository(): UserDatabaseRepository
}
