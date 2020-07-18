plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":database"))
    implementation(project(":di:viewmodel"))
    implementation(project(":feature-userlist:api"))
    implementation(project(":model"))
    implementation(project(":network:api"))
    implementation(project(":ui"))

    implementation(Dependencies.coroutinesAndroid)

    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.androidxViewModel)
    implementation(Dependencies.androidxLifecycleRuntime)
    implementation(Dependencies.androidxRecyclerView)
    implementation(Dependencies.androidxSwipeRefreshLayout)

    implementation(Dependencies.cicerone)
    implementation(Dependencies.groupie)
    implementation(Dependencies.groupieViewBinding)
}
