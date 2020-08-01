package xyz.pavelkorolev.randomuser.about.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import xyz.pavelkorolev.randomuser.about.presentation.AboutViewModel
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelKey

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
internal abstract class AboutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindViewModel(impl: AboutViewModel): ViewModel
}
