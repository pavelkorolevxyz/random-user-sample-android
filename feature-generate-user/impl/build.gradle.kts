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
        buildConfig = false
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

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":database:api"))
    implementation(project(":di:viewmodel"))
    implementation(project(":feature-generate-user:api"))
    implementation(project(":network:api"))
    implementation(project(":ui"))

    implementation(Dependencies.coroutinesAndroid)

    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.androidxViewModel)
    implementation(Dependencies.androidxLifecycleRuntime)
    implementation(Dependencies.androidxRecyclerView)

    implementation(Dependencies.cicerone)

    // Test dependencies
    testImplementation(project(":utils-test"))
    testImplementation(Dependencies.kotestRunnerJunit5)
    testImplementation(Dependencies.kotestAssert)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.turbine)
}
