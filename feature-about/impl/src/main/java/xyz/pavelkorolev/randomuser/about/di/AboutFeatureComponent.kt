package xyz.pavelkorolev.randomuser.about.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Router
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
