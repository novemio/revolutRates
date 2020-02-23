package versioning

import java.io.File
import java.util.Properties

/**
 * Created by novemio on 7/16/19.
 */
class VersionProperties(private var file: File) {
	
	var versionMajor = 0
	var versionMinor = 0
	var versionPatch = 0
	var versionRcBuild = 0
	var isSnapshot = false
	private val versionProps = Properties()
	
	init {
		if (file.canRead()) {
			versionProps.load(file.inputStream())
			versionMajor = versionProps.getProperty("VERSION_MAJOR").toInt()
			versionMinor = versionProps.getProperty("VERSION_MINOR").toInt()
			versionPatch = versionProps.getProperty("VERSION_PATCH").toInt()
			versionRcBuild = versionProps.getProperty("VERSION_RC_BUILD").toInt()
			isSnapshot = versionProps.getProperty("IS_SNAPSHOT")!!.toBoolean()
		}
	}
	
	fun saveConfiguration() {
		versionProps.setProperty("VERSION_MAJOR", versionMajor.toString())
		versionProps.setProperty("VERSION_MINOR", versionMinor.toString())
		versionProps.setProperty("VERSION_PATCH", versionPatch.toString())
		versionProps.setProperty("VERSION_RC_BUILD", versionRcBuild.toString())
		versionProps.setProperty("IS_SNAPSHOT", isSnapshot.toString())
		versionProps.store(file.bufferedWriter(), null)
	}
}