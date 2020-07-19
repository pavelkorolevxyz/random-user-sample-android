package xyz.pavelkorolev.randomuser.userlist.impl.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelKey
import xyz.pavelkorolev.randomuser.userlist.impl.presentation.UserListViewModel

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
internal abstract class UserListViewModelModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindViewModel(impl: UserListViewModel): ViewModel
}
