package com.novem.lib.core.di.routes

import com.novem.lib.core.presentation.CoreFragment
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
	AnnotationTarget.FUNCTION,
	AnnotationTarget.PROPERTY_GETTER,
	AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class RouteKey(val fragment: KClass<out CoreFragment<*, *, *>>)