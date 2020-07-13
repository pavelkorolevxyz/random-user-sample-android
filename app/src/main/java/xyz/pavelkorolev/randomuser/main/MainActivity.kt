package xyz.pavelkorolev.randomuser.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.splash.api.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.impl.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.impl.di.UserListFeatureDependencies

class MainActivity : AppCompatActivity(),
    SplashFeatureDependencies.DepProvider,
    UserListFeatureDependencies.DepProvider {

    private val component: MainActivityComponent by lazyUi {
        DaggerMainActivityComponent.factory().create(this)
    }

    private val navigatorHolder: NavigatorHolder by lazyUi {
        component.navigatorHolder()
    }

    private val navigator: Navigator by lazy {
        component.navigator()
    }

    private val splashFeatureApi: SplashFeatureApi by lazy {
        component.splashFeatureApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) return
        splashFeatureApi.setRootSplash()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun provideSplashFragmentDependencies(): SplashFeatureDependencies = component

    override fun provideUserListFragmentDependencies(): UserListFeatureDependencies = component
}
