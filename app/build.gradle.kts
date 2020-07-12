plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.id

        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }

    signingConfigs {
        getByName("debug")
        create("release") {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            val signingConfig = loadSigningConfig(keystorePropertiesFile)
                ?: loadSigningConfigFromEnvironment()

            storeFile = keystorePropertiesFile.parentFile.resolve(signingConfig.keystoreFile)
            storePassword = signingConfig.password
            keyAlias = signingConfig.alias
            keyPassword = signingConfig.aliasPassword
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isShrinkResources = false
            isMinifyEnabled = false
        }
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true

            signingConfig = signingConfigs.getByName("release")
        }
    }

    lintOptions {
        isCheckReleaseBuilds = false
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
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.material)

    debugImplementation(Dependencies.leakCanary)
}
