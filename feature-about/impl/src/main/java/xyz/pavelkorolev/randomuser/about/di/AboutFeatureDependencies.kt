package xyz.pavelkorolev.randomuser.about.di

import ru.terrakok.cicerone.Router

interface AboutFeatureDependencies {

    interface DepProvider {
        fun provideAboutFragmentDependencies(): AboutFeatureDependencies
    }

    fun router(): Router
}
