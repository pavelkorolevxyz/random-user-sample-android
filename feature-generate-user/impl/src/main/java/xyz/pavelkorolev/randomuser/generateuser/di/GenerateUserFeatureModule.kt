package xyz.pavelkorolev.randomuser.generateuser.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.generateuser.api.GenerateUserFeatureApi

@Module
abstract class GenerateUserFeatureModule {

    @Binds
    abstract fun bindGenerateUserFeatureApi(impl: GenerateUserFeatureApiImpl): GenerateUserFeatureApi
}
