package xyz.pavelkorolev.randomuser.userlist.di

import ru.terrakok.cicerone.Router

interface UserListFragmentDependencies {

    interface DepProvider {
        fun provideUserListFragmentDependencies(): UserListFragmentDependencies
    }

    fun router(): Router
}
