package signing

import org.gradle.api.Project
import java.io.File
import java.util.Properties

class BuildSign(project: Project, properties: Properties) {
	val storeFile: File = project.file(properties["storeFile"]!!)
	val storePassword: String = properties["storePassword"] as String
	val keyAlias: String = properties["keyAlias"] as String
	val keyPassword: String = properties["keyPassword"] as String
	
}

object Signing {
	
	fun getConfiguration(rootProject: Project): SigningContainer {
		val devKeystorePropertiesFile = rootProject.file("config/signing/keystore.properties")
		val devKeystoreProperties = Properties()
		
		devKeystoreProperties.load(devKeystorePropertiesFile.inputStream())
		
		val releaseKeyPropFile = rootProject.file("config/signing/releaseKeystore.properties")
		val releaseKeystoreProperties = Properties()
		
		if (releaseKeyPropFile.exists()) {
			releaseKeystoreProperties.load(releaseKeyPropFile.inputStream())
		} else {
			releaseKeystoreProperties.load(devKeystorePropertiesFile.inputStream())
		}
		return SigningContainer(
			BuildSign(rootProject, devKeystoreProperties),
			BuildSign(rootProject, releaseKeystoreProperties)
		)
		
	}
	
	class SigningContainer(val debugSign: BuildSign, val releaseSign: BuildSign)
}

fun Project.getConfiguration() = Signing.getConfiguration(this)
