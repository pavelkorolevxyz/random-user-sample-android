package xyz.pavelkorolev.randomuser.generateuser.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelKey
import xyz.pavelkorolev.randomuser.generateuser.presentation.GenerateUserViewModel

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
internal abstract class GenerateUserViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GenerateUserViewModel::class)
    abstract fun bindViewModel(impl: GenerateUserViewModel): ViewModel
}
