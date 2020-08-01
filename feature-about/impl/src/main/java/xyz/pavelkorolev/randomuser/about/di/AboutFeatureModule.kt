package xyz.pavelkorolev.randomuser.about.di

import dagger.Binds
import dagger.Module
import xyz.pavelkorolev.randomuser.about.AboutFeatureApi

@Module
abstract class AboutFeatureModule {

    @Binds
    abstract fun bindAboutFeatureApi(impl: AboutFeatureApiImpl): AboutFeatureApi
}
