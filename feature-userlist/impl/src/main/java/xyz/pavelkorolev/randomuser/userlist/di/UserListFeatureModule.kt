package xyz.pavelkorolev.randomuser.userlist.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApi
import xyz.pavelkorolev.randomuser.userlist.UserListFeatureApiImpl

@Module
abstract class UserListFeatureModule {

    @Binds
    abstract fun bindsUserListFeatureApi(impl: UserListFeatureApiImpl): UserListFeatureApi
}
