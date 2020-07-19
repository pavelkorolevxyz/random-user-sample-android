package xyz.pavelkorolev.randomuser.userlist.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi

@Module
abstract class UserListFeatureModule {

    @Binds
    abstract fun bindUserListFeatureApi(impl: UserListFeatureApiImpl): UserListFeatureApi
}
