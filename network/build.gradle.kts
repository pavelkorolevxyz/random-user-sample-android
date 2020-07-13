plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlinx-serialization")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Dependencies.ktorAndroid)
    implementation(Dependencies.ktorOkHttp)
    implementation(Dependencies.ktorLogging)
    implementation(Dependencies.ktorSerialization)
    implementation(Dependencies.kotlinxSerializationRuntime)
}
