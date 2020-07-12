package xyz.pavelkorolev.randomuser.userlist.impl.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi
import xyz.pavelkorolev.randomuser.userlist.impl.UserListFeatureApiImpl

@Module
abstract class UserListFeatureModule {

    @Binds
    abstract fun bindsUserListFeatureApi(impl: UserListFeatureApiImpl): UserListFeatureApi
}
