package xyz.pavelkorolev.randomuser.main

import android.os.Bundle
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.BaseActivity
import xyz.pavelkorolev.randomuser.about.di.AboutFeatureDependencies
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.generateuser.di.GenerateUserFeatureDependencies
import xyz.pavelkorolev.randomuser.splash.SplashFeatureApi
import xyz.pavelkorolev.randomuser.splash.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.di.UserListFeatureDependencies

class MainActivity :
    BaseActivity(),
    SplashFeatureDependencies.DepProvider,
    UserListFeatureDependencies.DepProvider,
    GenerateUserFeatureDependencies.DepProvider,
    AboutFeatureDependencies.DepProvider {

    private val component: MainActivityComponent by lazyUi {
        val provider = application as MainActivityDependencies.DepProvider
        DaggerMainActivityComponent.factory()
            .create(
                this,
                provider.provideMainActivityDependencies()
            )
    }

    private val navigatorHolder: NavigatorHolder by lazyUi {
        component.navigatorHolder()
    }

    private val router: Router by lazyUi {
        component.router()
    }

    private val navigator: Navigator by lazyUi {
        component.navigator()
    }

    private val splashFeatureApi: SplashFeatureApi by lazyUi {
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

    override fun provideSplashFeatureDependencies(): SplashFeatureDependencies = component

    override fun provideUserListFeatureDependencies(): UserListFeatureDependencies = component

    override fun provideGenerateUserFeatureDependencies(): GenerateUserFeatureDependencies = component

    override fun provideAboutFeatureDependencies(): AboutFeatureDependencies = component

    override fun onBackPressed() {
        router.exit()
    }
}
