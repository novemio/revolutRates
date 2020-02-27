package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import versioning.AppVersion
import versioning.VersionProperties
import java.io.File

/**
 * Created by novemio on 7/16/19.
 */
open class CreateRelease : DefaultTask() {
	private val file = File("${project.rootDir}/config/version.properties")
	
	@TaskAction
	fun increment() {
		description = "CreateRelease"
		
		val appVersion = AppVersion(project)
		project.exec {
			workingDir(project.rootDir)
			commandLine("./config/release.sh", appVersion.appVersionName, appVersion.appVersionShort)
		}
		
	}
}