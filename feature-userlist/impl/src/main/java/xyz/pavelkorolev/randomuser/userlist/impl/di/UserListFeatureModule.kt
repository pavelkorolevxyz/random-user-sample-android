package xyz.pavelkorolev.randomuser.userlist.impl.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi

@Module
abstract class UserListFeatureModule {

    @Binds
    abstract fun bindUserListFeatureApi(impl: UserListFeatureApiImpl): UserListFeatureApi
}
