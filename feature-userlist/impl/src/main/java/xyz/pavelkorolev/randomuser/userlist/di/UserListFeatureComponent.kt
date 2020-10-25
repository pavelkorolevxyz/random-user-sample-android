package xyz.pavelkorolev.randomuser.userlist.di

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.userlist.presentation.UserListViewModel
import xyz.pavelkorolev.randomuser.userlist.view.UserListController

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

    fun viewModel(): UserListViewModel

    fun listController(): UserListController

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: UserListFeatureDependencies
        ): UserListFeatureComponent
    }
}
