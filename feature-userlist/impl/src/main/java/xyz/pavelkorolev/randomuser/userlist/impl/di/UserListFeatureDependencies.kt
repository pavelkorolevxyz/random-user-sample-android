package xyz.pavelkorolev.randomuser.userlist.impl.di

import ru.terrakok.cicerone.Router

interface UserListFeatureDependencies {

    interface DepProvider {
        fun provideUserListFragmentDependencies(): UserListFeatureDependencies
    }

    fun router(): Router
}
