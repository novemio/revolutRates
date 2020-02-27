package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import versioning.AppVersion
import versioning.VersionProperties
import java.io.File

/**
 * Created by novemio on 7/16/19.
 */
open class IncrementPatchNumber : DefaultTask() {
	private val file = File("${project.rootDir}/config/version.properties")
	
	@TaskAction
	fun increment() {
		description = "Increment version Patch Number"
		println("-------------------$description------------------------")
		val vp = VersionProperties(file)
		vp.apply {
			println("Increment versionPatch from $versionPatch to ${++versionPatch}")
			versionRcBuild = 0
			println("Reset release candidate number to $versionRcBuild")
			
			AppVersion(vp).run {
				println("New version is $appVersionName")
				println("New version code is $appVersionCode")
			}
			saveConfiguration()
		}
		println("-------------------------------------------------------------------------------")
	}
}