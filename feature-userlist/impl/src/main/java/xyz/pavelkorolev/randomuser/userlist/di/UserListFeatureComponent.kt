package xyz.pavelkorolev.randomuser.userlist.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.userlist.presentation.UserListViewModel

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

    @ExperimentalCoroutinesApi
    fun viewModel(): UserListViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: UserListFeatureDependencies
        ): UserListFeatureComponent
    }
}
