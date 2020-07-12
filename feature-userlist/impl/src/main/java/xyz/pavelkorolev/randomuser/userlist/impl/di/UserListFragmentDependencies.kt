package xyz.pavelkorolev.randomuser.userlist.impl.di

import ru.terrakok.cicerone.Router

interface UserListFragmentDependencies {

    interface DepProvider {
        fun provideUserListFragmentDependencies(): UserListFragmentDependencies
    }

    fun router(): Router
}
