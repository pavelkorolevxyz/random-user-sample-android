package xyz.pavelkorolev.randomuser.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.extensions.lazyUi
import xyz.pavelkorolev.randomuser.splash.SplashFragmentDependencies
import xyz.pavelkorolev.randomuser.splash.SplashScreen

class MainActivity : AppCompatActivity(), SplashFragmentDependencies.DepProvider {

    private val component: MainActivityComponent by lazyUi {
        DaggerMainActivityComponent.factory().create(this)
    }

    private val navigatorHolder: NavigatorHolder by lazyUi {
        component.navigatorHolder()
    }

    private val router: Router by lazyUi {
        component.router()
    }

    private val navigator: Navigator by lazy {
        component.navigator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) return
        router.navigateTo(SplashScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun provideSplashFragmentDependencies(): SplashFragmentDependencies = component
}
