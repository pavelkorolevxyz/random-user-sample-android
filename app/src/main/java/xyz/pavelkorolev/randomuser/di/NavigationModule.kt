package xyz.pavelkorolev.randomuser.di

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Scope

@Scope
internal annotation class NavigationScope

@Module
internal object NavigationModule {

    @NavigationScope
    @Provides
    fun providesCicerone(): Cicerone<Router> = Cicerone.create()

    @NavigationScope
    @Provides
    fun providesRouter(
        cicerone: Cicerone<Router>
    ): Router = cicerone.router

    @NavigationScope
    @Provides
    fun providesNavigatorHolder(
        cicerone: Cicerone<Router>
    ): NavigatorHolder = cicerone.navigatorHolder

    @NavigationScope
    @Provides
    fun provideNavigator(
        activity: FragmentActivity
    ): Navigator = SupportAppNavigator(activity, android.R.id.content)
}
