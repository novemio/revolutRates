import kotlin.String
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
  const val appcompat: String = "1.1.0"

  const val core_testing: String = "2.1.0"

  const val cardview: String = "1.0.0"

  const val constraintlayout: String = "1.1.3"

  const val core_ktx: String = "1.1.0" // available: "1.2.0"

  const val androidx_databinding: String = "3.5.0" // available: "3.6.0"

  const val lifecycle_extensions: String = "2.1.0" // available: "2.2.0"

  const val androidx_navigation: String = "2.1.0" // available: "2.2.1"

  const val paging_runtime: String = "2.1.0" // available: "2.1.1"

  const val recyclerview: String = "1.0.0" // available: "1.1.0"

  const val androidx_room: String = "2.1.0" // available: "2.2.4"

  const val espresso_core: String = "3.2.0"

  const val androidx_test: String = "1.2.0"

  const val lottie: String = "3.3.1"

  const val aapt2: String = "3.5.0-5435860" // available: "3.6.0-6040484"

  const val com_android_tools_build_gradle: String = "3.5.0" // available: "3.6.0"

  const val lint_gradle: String = "26.5.0" // available: "26.6.0"

  const val stetho: String = "1.5.1"

  const val timberkt: String = "1.5.1"

  const val com_github_bumptech_glide: String = "4.9.0" // available: "4.11.0"

  const val reactivenetwork_rx2: String = "3.0.6"

  const val com_google_auto_value: String = "1.6.6" // available: "1.7"

  const val com_google_dagger: String = "2.24" // available: "2.26"

  const val google_services: String = "4.3.0" // available: "4.3.3"

  const val com_squareup_moshi: String = "1.8.0" // available: "1.9.2"

  const val com_squareup_okhttp3: String = "4.2.0" // available: "4.4.0"

  const val com_squareup_retrofit2: String = "2.6.1" // available: "2.7.1"

  const val de_fayard_buildsrcversions_gradle_plugin: String = "0.7.0"

  const val android_junit5: String = "1.5.1.0" // available: "1.5.2.0"

  const val de_mannodermaus_junit5: String = "1.1.0" // available: "1.2.0"

  const val io_gitlab_arturbosch_detekt_gradle_plugin: String = "1.0.0-RC15"
       // available: "1.6.0"

  const val io_mockk: String = "1.9.3"

  const val rxandroid: String = "2.1.1"

  const val rxjava: String = "2.2.12" // available: "2.2.18"

  const val rxkotlin: String = "2.4.0"

  const val junit: String = "4.12" // available: "4.13"

  const val org_jetbrains_kotlin: String = "1.3.50" // available: "1.3.61"

  const val org_junit_jupiter: String = "5.5.2" // available: "5.6.0"

  /**
   *
   * See issue 19: How to update Gradle itself?
   * https://github.com/jmfayard/buildSrcVersions/issues/19
   */
  const val gradleLatestVersion: String = "6.2.1"

  const val gradleCurrentVersion: String = "5.6.2"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
  inline get() =
      id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
