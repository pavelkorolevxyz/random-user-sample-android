package xyz.pavelkorolev.randomuser.userlist.impl.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Router

@Component(
    dependencies = [
        UserListFragmentDependencies::class
    ]
)
interface UserListFeatureComponent {

    fun router(): Router

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: UserListFragmentDependencies
        ): UserListFeatureComponent
    }
}
