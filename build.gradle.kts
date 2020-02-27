
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://www.jitpack.io") }
    }
    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.android_junit5)
        classpath("com.google.gms:google-services:4.3.0")
        classpath(Libs.navigation_safe_args_gradle_plugin)
    }
}
repositories {
    jcenter()
    google()
    maven { setUrl("https://maven.google.com") }
}

plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC15"
}


allprojects {
    repositories {
        google()
        jcenter()
    }


}
subprojects {
    apply(from = "$rootDir/config/quality/quality.gradle.kts")


    // Junit 5 For Kotlin
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}
tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }

}