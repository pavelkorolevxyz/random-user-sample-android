package xyz.pavelkorolev.randomuser.di

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import xyz.pavelkorolev.randomuser.AppNavigator
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
    ): NavigatorHolder = cicerone.getNavigatorHolder()

    @NavigationScope
    @Provides
    fun provideNavigator(
        activity: FragmentActivity
    ): Navigator = AppNavigator(activity, android.R.id.content)
}
