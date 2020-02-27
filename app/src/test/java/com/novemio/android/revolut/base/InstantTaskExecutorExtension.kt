package com.dropit.ambassador.android.utils

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * A JUnit Test Rule that swaps the background executor used by the Architecture Components with a
 * different one which executes each task synchronously.
 *
 *
 * You can use this rule for your host side tests that use Architecture Components.
 */
class InstantTaskExecutorExtension : BeforeAllCallback, AfterAllCallback {
	
	override fun beforeAll(context: ExtensionContext?) {
		ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
			override fun executeOnDiskIO(runnable: Runnable) {
				runnable.run()
			}
			
			override fun postToMainThread(runnable: Runnable) {
				runnable.run()
			}
			
			override fun isMainThread(): Boolean {
				return true
			}
		})
	}
	
	override fun afterAll(context: ExtensionContext?) {
		ArchTaskExecutor.getInstance().setDelegate(null)
	}
	
}
