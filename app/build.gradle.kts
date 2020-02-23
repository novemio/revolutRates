import de.mannodermaus.gradle.plugins.junit5.junitPlatform

buildscript {
    repositories {
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
}


plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")

}

android {

    lintOptions {
        isAbortOnError = false
        disable("ContentDescription")
    }
    androidExtensions {
        isExperimental = true
    }

    // 4) JUnit 5 will bundle in files with identical paths; exclude them
    packagingOptions {
        exclude("META-INF/LICENSE*")
        exclude("META-INF/kotlin-logging.kotlin_module")
    }

    compileSdkVersion(Config.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetVersion)
        testInstrumentationRunner = Config.Android.instrumentationRunner
        testInstrumentationRunnerArgument(
            "runnerBuilder",
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
        )

        applicationId = Config.Android.applicationId
        Config.Android.versioning(rootProject).run {
            versionCode = 1
            versionName = "1"
        }
    }

    buildTypes {

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = false
//			isDebuggable = true
            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        junitPlatform {
            // Here it is!
            unitTests.isReturnDefaultValues = true
        }
        unitTests.isReturnDefaultValues = true
    }

    dataBinding {
        isEnabled = true
    }

}

dependencies {
    implementation(project(":core"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(Libs.appcompat)
    implementation(Libs.core_ktx)

    //log
    implementation(Libs.timberkt)

    //Android libs
    implementation(Libs.constraintlayout)
    implementation(Libs.recyclerview)
    implementation(Libs.cardview)

    //Navigation
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)
    implementation(Libs.navigation_runtime_ktx)

    //Stetho
    implementation(Libs.stetho)

    //Room database
    implementation(Libs.room_rxjava2)
    implementation(Libs.room_runtime)
    testImplementation(Libs.room_testing)

    //rx java
    implementation(Libs.rxjava)
    implementation(Libs.rxkotlin)
    implementation(Libs.rxandroid)

    //Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.adapter_rxjava2)
    implementation(Libs.converter_moshi)

    //ok http
    implementation(Libs.okhttp)
    implementation(Libs.logging_interceptor)

    //dagger
    implementation(Libs.dagger)
    implementation(Libs.dagger_android_support)
    kapt(Libs.dagger_android_processor)
    kapt(Libs.dagger_compiler)

    //Moshi
    implementation(Libs.moshi_kotlin)
    kapt(Libs.moshi_kotlin_codegen)

    //image loading :Glide
    implementation(Libs.glide)
    kapt(Libs.com_github_bumptech_glide_compiler)

    //Tests

    //JUnit 5
    testImplementation(Libs.junit_jupiter_params)
    testImplementation(Libs.junit_jupiter_api)
    androidTestImplementation(Libs.junit_jupiter_api)

    testRuntimeOnly(Libs.junit_jupiter_engine)
    androidTestRuntimeOnly(Libs.junit_jupiter_engine)


    androidTestImplementation(Libs.android_test_core)
    androidTestRuntimeOnly(Libs.android_test_runner)


    testImplementation(Libs.mockk)
    //	testImplementation(Libs.mock)
    androidTestImplementation(Libs.mockk_android)


    androidTestImplementation(Libs.core_testing)
    testImplementation(Libs.core_testing)

    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.androidx_test_core)

    androidTestImplementation(Libs.espresso_core)

}

configurations.all {
    resolutionStrategy.force("org.jetbrains.kotlin:kotlin-reflect:1.3.40")

}
