package com.novem.lib.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory
@Inject
constructor(private val viewModels: Map<ViewModelKey, @JvmSuppressWildcards Provider<ViewModel>>) : IViewModelFactory {
	
	override fun getViewModelByClass(clazz: KClass<out Any>): Class<out ViewModel>? =
		viewModels.keys.find {
			it.kClass.simpleName == clazz.simpleName
		}?.viewModel?.java
	
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		
		val key = viewModels.keys.find {
			it.viewModel.simpleName == modelClass.simpleName
		}
		
		val viewModel = viewModels[key]?.get()
		
		viewModel?.let {
			if (viewModel::class.java != modelClass) {
				println("error")
				throw ExceptionInInitializerError(
					"${viewModel::class.java.simpleName} not equals ${modelClass.simpleName} "
				)
			}
		}
		
		return viewModel as T
	}
	
}

interface IViewModelFactory : ViewModelProvider.Factory {
	
	fun getViewModelByClass(clazz: KClass<out Any>): Class<out ViewModel>?
	
}