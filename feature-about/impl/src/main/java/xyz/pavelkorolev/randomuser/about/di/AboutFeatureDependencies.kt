package xyz.pavelkorolev.randomuser.about.di

import android.app.Application
import com.github.terrakok.cicerone.Router

interface AboutFeatureDependencies {

    interface DepProvider {
        fun provideAboutFeatureDependencies(): AboutFeatureDependencies
    }

    fun app(): Application

    fun router(): Router
}
