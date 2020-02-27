package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import versioning.AppVersion
import versioning.VersionProperties
import java.io.File

/**
 * Created by novemio on 7/16/19.
 */
open class UpdateRelease : DefaultTask() {
	@TaskAction
	fun increment() {
		description = "UpdateRelease"
		
		val appVersion = AppVersion(project)
		project.exec {
			workingDir(project.rootDir)
			commandLine("./config/updateRelease.sh", appVersion.appVersionName, appVersion.appVersionShort)
		}
		
	}
}