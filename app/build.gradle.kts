import com.android.build.gradle.internal.tasks.factory.dependsOn
import de.mannodermaus.gradle.plugins.junit5.junitPlatform
import signing.getConfiguration

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
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    id("com.google.firebase.appdistribution")

}
apply(from = "../config/firebase/firebaseManager.gradle.kts")



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

    signingConfigs {
        val config = rootProject.getConfiguration()
        val debugSign = config.debugSign
        val releaseSign = config.releaseSign
        named("debug").configure {
            storeFile = debugSign.storeFile
            storePassword = debugSign.storePassword
            keyAlias = debugSign.keyAlias
            keyPassword = debugSign.keyPassword
        }
        create("release") {
            storeFile = releaseSign.storeFile
            storePassword = releaseSign.storePassword
            keyAlias = releaseSign.keyAlias
            keyPassword = releaseSign.keyPassword
        }

    }


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
            versionCode = appVersionCode
            versionName = appVersionName
        }
    }

    buildTypes {

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders = mapOf(
                "crashlyticsCollectionEnabled" to "false"
            )
            extra.set("enableCrashlytics", false)
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = false
            firebaseAppDistribution.apply {
                serviceCredentialsFile = extra.get("firebaseCredentialsKeyPath") as String
                groupsFile = extra.get("firebaseGroupsFilePath") as String
                releaseNotesFile  = extra.get("firebaseReleaseNotesPath") as String
            }


            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders = mapOf(
                "crashlyticsCollectionEnabled" to "true"
            )
            signingConfig = signingConfigs.getByName("release")
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

    implementation(Libs.lottie)
    // Add the Firebase Crashlytics dependency.
    implementation("com.google.firebase:firebase-crashlytics:17.0.0-beta01")
    implementation("com.google.firebase:firebase-analytics:17.2.2")

    implementation(Libs.reactivenetwork_rx2)
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

tasks {
    val incrementPatchNumber by register("incrementPatchNumber", tasks.IncrementPatchNumber::class)
    val incrementRCBuildNumber by register(
        "incrementBuildNumber",
        tasks.IncrementBuildNumber::class
    )
    val incrementPatchAndRCBuildNumber by register(
        "incrementPatchAndBuildNumber",
        tasks.IncrementBuildNumber::class
    ).apply {
        dependsOn(incrementPatchNumber)
    }

    val createRelease by register("createRelease", tasks.CreateRelease::class).apply {
        dependsOn(incrementRCBuildNumber)
    }

    val updateRelease by register("updateRelease", tasks.UpdateRelease::class).apply {
        dependsOn(incrementRCBuildNumber)
    }

}
