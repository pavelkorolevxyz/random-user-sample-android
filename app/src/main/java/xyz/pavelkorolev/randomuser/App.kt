package xyz.pavelkorolev.randomuser

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.di.DaggerAppComponent

class App : Application() {

    private val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    companion object {
        fun get(activity: Activity): App = activity.application as App
        fun get(fragment: Fragment): App = get(fragment.requireActivity())
    }
}
