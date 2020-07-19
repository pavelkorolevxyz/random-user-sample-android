package xyz.pavelkorolev.randomuser.splash.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi

@Module
abstract class SplashFeatureModule {

    @Binds
    abstract fun bindSplashFeatureApi(impl: SplashFeatureApiImpl): SplashFeatureApi
}
