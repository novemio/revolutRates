buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { setUrl("https://www.jitpack.io") }
        
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.21")
    }
}
repositories {
    jcenter()
    google()
    maven { setUrl("https://maven.google.com") }
}


plugins {
    `kotlin-dsl`
}
apply (plugin= "kotlin")

dependencies {
    implementation( "com.android.tools.build:gradle:3.5.0")
    implementation ("com.android.tools.build:gradle-api:3.5.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.41")
}


        