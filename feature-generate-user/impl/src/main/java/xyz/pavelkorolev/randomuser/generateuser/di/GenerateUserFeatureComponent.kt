package xyz.pavelkorolev.randomuser.generateuser.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.terrakok.cicerone.Router
import xyz.pavelkorolev.randomuser.generateuser.presentation.GenerateUserViewModel

@Component(
    modules = [
        GenerateUserViewModelModule::class
    ],
    dependencies = [
        GenerateUserFeatureDependencies::class
    ]
)
interface GenerateUserFeatureComponent {

    fun router(): Router

    @ExperimentalCoroutinesApi
    fun viewModel(): GenerateUserViewModel

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            dependencies: GenerateUserFeatureDependencies
        ): GenerateUserFeatureComponent
    }
}
