package com.novemio.android.revolut.base

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

internal class RxSchedulersExtension : BeforeAllCallback {
	
	override fun beforeAll(context: ExtensionContext?) {
		RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
		RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
		RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
		RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
	}
}

