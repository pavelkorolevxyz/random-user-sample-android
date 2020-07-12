package xyz.pavelkorolev.randomuser.splash.impl

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import xyz.pavelkorolev.randomuser.core.extensions.lazyUi
import xyz.pavelkorolev.randomuser.splash.impl.di.DaggerSplashFeatureComponent
import xyz.pavelkorolev.randomuser.splash.impl.di.SplashFeatureComponent
import xyz.pavelkorolev.randomuser.splash.impl.di.SplashFeatureDependencies
import xyz.pavelkorolev.randomuser.userlist.api.UserListFeatureApi

class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val component: SplashFeatureComponent by lazyUi {
        val provider = activity as SplashFeatureDependencies.DepProvider
        DaggerSplashFeatureComponent.factory()
            .create(
                this,
                provider.provideSplashFragmentDependencies()
            )
    }

    private val userListFeatureApi: UserListFeatureApi by lazyUi { component.userListFeatureApi() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).post {
            userListFeatureApi.replaceUserList()
        }
    }

    companion object {
        fun newInstance(): Fragment = SplashFragment()
    }
}
