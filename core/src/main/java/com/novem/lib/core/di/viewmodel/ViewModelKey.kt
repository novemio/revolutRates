package com.novem.lib.core.di.viewmodel

import androidx.lifecycle.ViewModel
import com.novem.lib.core.AndroidComponent
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
	AnnotationTarget.FUNCTION,
	AnnotationTarget.PROPERTY_GETTER,
	AnnotationTarget.PROPERTY_SETTER
)

@Retention(AnnotationRetention.RUNTIME)
@MapKey(unwrapValue = false)
annotation class ViewModelKey(val kClass: KClass<out AndroidComponent>, val viewModel: KClass<out ViewModel>)