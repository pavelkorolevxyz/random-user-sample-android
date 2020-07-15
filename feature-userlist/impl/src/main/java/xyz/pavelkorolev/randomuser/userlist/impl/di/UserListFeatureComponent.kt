package xyz.pavelkorolev.randomuser.userlist.impl.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelFactoryModule
import xyz.pavelkorolev.randomuser.di.viewmodel.ViewModelKey
import xyz.pavelkorolev.randomuser.userlist.impl.presentation.UserListViewModel

@Component(
    modules = [
        UserListViewModelModule::class
    ],
    dependencies = [
        UserListFeatureDependencies::class
    ]
)
interface UserListFeatureComponent {

    fun router(): Router

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: UserListFeatureDependencies
        ): UserListFeatureComponent
    }
}

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
