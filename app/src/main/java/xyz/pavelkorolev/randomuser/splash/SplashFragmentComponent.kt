package xyz.pavelkorolev.randomuser.splash

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Router

interface SplashFragmentDependencies {

    interface DepProvider {
        fun provideSplashFragmentDependencies(): SplashFragmentDependencies
    }

    fun router(): Router
}

@Component(
    dependencies = [
        SplashFragmentDependencies::class
    ]
)
interface SplashFragmentComponent {

    fun router(): Router

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: SplashFragmentDependencies
        ): SplashFragmentComponent
    }
}
