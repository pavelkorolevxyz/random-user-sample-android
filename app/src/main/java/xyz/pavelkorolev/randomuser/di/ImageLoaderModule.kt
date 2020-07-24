package xyz.pavelkorolev.randomuser.di

import dagger.Module
import dagger.Provides
import xyz.pavelkorolev.randomuser.ImageLoader
import javax.inject.Scope

@Scope
internal annotation class ImageLoaderScope

@Module
internal object ImageLoaderModule {

    @ImageLoaderScope
    @Provides
    fun provideImageLoader(): ImageLoader = ImageLoader()
}
