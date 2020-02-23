plugins {
	id("com.android.library")
	id("kotlin-android")
	id("kotlin-android-extensions")
	id("kotlin-kapt")
}

android {
	compileSdkVersion(28)

	defaultConfig {
		minSdkVersion(21)
		targetSdkVersion(28)
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	dataBinding {
		isEnabled = true
	}
}


dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
	implementation(Libs.kotlin_stdlib_jdk8)
	implementation(Libs.appcompat)
	implementation(Libs.core_ktx)

	//Android libs
	implementation(Libs.constraintlayout)
	implementation(Libs.recyclerview)
	implementation(Libs.cardview)
	implementation(Libs.cardview)
	implementation(Libs.paging_runtime)
	implementation(Libs.lifecycle_extensions)

	//Navigation
	implementation(Libs.navigation_fragment_ktx)
	implementation(Libs.navigation_ui_ktx)

	//Room database
	implementation(Libs.room_rxjava2)
	implementation(Libs.room_runtime)
	kapt(Libs.room_compiler)
	testImplementation(Libs.room_testing)

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

	//autoValue
	implementation(Libs.auto_value_annotations)
	kapt(Libs.auto_value)
	//Moshi
	implementation(Libs.moshi_kotlin)
	kapt(Libs.moshi_kotlin_codegen)

	//rx java

	implementation(Libs.rxjava)
	implementation(Libs.rxkotlin)
	implementation(Libs.rxandroid)

	//image loading :Glide
	implementation(Libs.glide)
	kapt(Libs.com_github_bumptech_glide_compiler)

	testImplementation(Libs.junit)
	androidTestImplementation(Libs.androidx_test_runner)
	androidTestImplementation(Libs.espresso_core)
}



