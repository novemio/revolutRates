import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val appcompat: String = "1.1.0"

    const val core_testing: String =  "2.1.0"

    const val cardview: String = "1.0.0"

    const val constraintlayout: String = "1.1.3"

    const val core_ktx: String = "1.1.0"

    const val androidx_databinding: String = "3.5.0"

    const val lifecycle_extensions: String =  "2.1.0"

    const val navigation_fragment_ktx: String =  "2.1.0"

    const val navigation_runtime_ktx: String =  "2.1.0"

    const val navigation_safe_args_gradle_plugin: String = "2.1.0"

    const val navigation_ui_ktx: String =  "2.1.0"

    const val paging_runtime: String = "2.1.0"

    const val recyclerview: String = "1.0.0"

    const val androidx_room: String = "2.1.0"

    const val espresso_core: String = "3.2.0"

    const val androidx_test: String = "1.2.0"

    const val aapt2: String = "3.5.0-5435860"

    const val com_android_tools_build_gradle: String = "3.5.0"

    const val lint_gradle: String = "26.5.0"

    const val crashlytics: String = "2.10.1"

    const val stetho: String = "1.5.1"

    const val timberkt: String = "1.5.1"

    const val com_github_bumptech_glide: String = "4.9.0"

    const val com_google_android_gms: String = "17.0.0"

    const val auto_value_annotations: String = "1.6.6"

    const val auto_value: String =  "1.6.6"

    const val com_google_dagger: String ="2.24"

    const val google_services: String =  "4.3.2"

    const val zxing_android_embedded: String =  "4.0.2"

    const val dexter: String = "5.0.0"

    const val com_novemio_checknamingconvention_gradle_plugin: String = "0.1.4"

    const val datasourceadapter: String = "0.3.3"

    const val onesignal: String = "3.11.3"

    const val com_squareup_moshi: String = "1.8.0"

    const val com_squareup_okhttp3: String = "4.2.0"

    const val com_squareup_retrofit2: String =  "2.6.1"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.5.0"

    const val android_junit5: String =  "1.5.1.0"

    const val de_mannodermaus_junit5: String = "1.1.0"


    const val io_fabric_tools_gradle: String =  "1.31.0"

    const val io_gitlab_arturbosch_detekt: String = "1.0.0-RC15"

    const val io_mockk: String = "1.9.3"

    const val rxandroid: String = "2.1.1"

    const val rxjava: String = "2.2.12"

    const val rxkotlin: String =  "2.4.0"

    const val junit: String = "4.12"

    const val org_jetbrains_kotlin: String ="1.3.50"

    const val org_junit_jupiter: String =  "5.5.2"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.6.2"

        const val currentVersion: String = "5.6.2"

        const val nightlyVersion: String = "6.0-20190910220033+0000"

        const val releaseCandidate: String = ""
    }
}
