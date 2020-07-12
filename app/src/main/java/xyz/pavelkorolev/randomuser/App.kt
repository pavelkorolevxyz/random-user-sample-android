package xyz.pavelkorolev.randomuser

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    private lateinit var cicerone: Cicerone<Router>
    val router: Router
        get() = cicerone.router
    val navigatorHolder: NavigatorHolder
        get() = cicerone.navigatorHolder

    override fun onCreate() {
        super.onCreate()

        cicerone = Cicerone.create()
    }

    companion object {
        fun get(activity: Activity): App = activity.application as App
        fun get(fragment: Fragment): App = get(fragment.requireActivity())
    }

}
