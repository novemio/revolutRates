
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
}


allprojects {
    repositories {
        google()
        jcenter()
    }


}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }

}