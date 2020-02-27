package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import versioning.AppVersion
import versioning.VersionProperties
import java.io.File

/**
 * Created by novemio on 7/16/19.
 */
open class IncrementBuildNumber : DefaultTask() {
	private val file = File("${project.rootDir}/config/version.properties")
	
	@TaskAction
	fun increment() {
		description = "Increment version Build Number"
		val vp = VersionProperties(file)
		vp.apply {
			println("Increment release candidate build number from $versionRcBuild to ${++versionRcBuild}")
		
			AppVersion(vp).run {
				println("New version is $appVersionName")
				println("New version code is $appVersionCode")
			}
			
			saveConfiguration()
		}
		
	}
}