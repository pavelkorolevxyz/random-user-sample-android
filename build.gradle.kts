buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.androidPlugin)
        classpath(Dependencies.kotlinPlugin)
    }
}

plugins {
    id(Dependencies.ktlintPlugin.id) version Dependencies.ktlintPlugin.version
    id(Dependencies.versionsPlugin.id) version Dependencies.versionsPlugin.version
    id(Dependencies.kotlinxSerializationPlugin.id) version Dependencies.kotlinxSerializationPlugin.version
}

allprojects {

    repositories {
        google()
        jcenter()
    }
}

subprojects {
    plugins.apply(Dependencies.ktlintPlugin.id)

    ktlint {
        version.set("0.37.2")
        android.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
        }
    }
}
