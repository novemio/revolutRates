package com.novem.lib.core.di.routes

import com.novem.lib.core.di.scopes.ActivityScope
import com.novem.lib.core.presentation.CoreFragment
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class RoutesFactory
@Inject
constructor(private val routes: Map<Class<out CoreFragment<*, *, *>>, @JvmSuppressWildcards Provider<Routes>>) :
	IRoutesFactory {

	override fun get(fragmentClass: Class<out CoreFragment<*, *, *>>): Routes? =
		routes[fragmentClass]?.get()
}