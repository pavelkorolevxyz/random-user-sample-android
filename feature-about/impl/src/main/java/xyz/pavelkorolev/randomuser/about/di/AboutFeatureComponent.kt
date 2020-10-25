package xyz.pavelkorolev.randomuser.about.di

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import xyz.pavelkorolev.randomuser.about.presentation.AboutViewModel

@Component(
    modules = [
        AboutViewModelModule::class
    ],
    dependencies = [
        AboutFeatureDependencies::class
    ]
)
interface AboutFeatureComponent {

    fun router(): Router

    fun viewModel(): AboutViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: AboutFeatureDependencies
        ): AboutFeatureComponent
    }
}
