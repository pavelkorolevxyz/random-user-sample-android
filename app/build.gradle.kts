plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
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
                ?: return@create

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

    buildFeatures {
        buildConfig = false
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

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":database:api"))
    implementation(project(":database:impl"))
    implementation(project(":di:viewmodel"))
    implementation(project(":feature-about:api"))
    implementation(project(":feature-about:impl"))
    implementation(project(":feature-generate-user:api"))
    implementation(project(":feature-generate-user:impl"))
    implementation(project(":feature-splash:api"))
    implementation(project(":feature-splash:impl"))
    implementation(project(":feature-userlist:api"))
    implementation(project(":feature-userlist:impl"))
    implementation(project(":image"))
    implementation(project(":logging"))
    implementation(project(":network:api"))
    implementation(project(":network:impl"))
    implementation(project(":startup"))
    implementation(project(":ui"))

    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.cicerone)

    implementation(Dependencies.ktorAndroid)

    debugImplementation(Dependencies.leakCanary)
}
