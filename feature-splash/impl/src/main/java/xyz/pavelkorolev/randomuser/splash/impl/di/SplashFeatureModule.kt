package xyz.pavelkorolev.randomuser.splash.impl.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.splash.api.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.impl.SplashFeatureApiImpl

@Module
abstract class SplashFeatureModule {

    @Binds
    abstract fun bindsSplashFeatureApi(impl: SplashFeatureApiImpl): SplashFeatureApi
}
