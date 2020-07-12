package xyz.pavelkorolev.randomuser.splash.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApiImpl

@Module
abstract class SplashFeatureModule {

    @Binds
    abstract fun bindsSplashFeatureApi(impl: SplashFeatureApiImpl): SplashFeatureApi
}
