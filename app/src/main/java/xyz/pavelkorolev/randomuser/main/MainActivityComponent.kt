package xyz.pavelkorolev.randomuser.main

import androidx.fragment.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.di.NavigationModule
import xyz.pavelkorolev.randomuser.di.NavigationScope
import xyz.pavelkorolev.randomuser.splash.SplashFragmentDependencies

@NavigationScope
@Component(
    modules = [
        NavigationModule::class
    ]
)
interface MainActivityComponent : SplashFragmentDependencies {

    fun navigatorHolder(): NavigatorHolder
    override fun router(): Router

    fun navigator(): Navigator

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: FragmentActivity
        ): MainActivityComponent
    }
}
