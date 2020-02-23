package com.novem.lib.core.presentation

import androidx.lifecycle.ViewModel
import com.novem.lib.core.domain.BaseInteractor

abstract class CoreViewModel : ViewModel(), IBaseViewModel {

	override val interactors: MutableSet<BaseInteractor> by lazy {
		mutableSetOf<BaseInteractor>()
	}

	override fun onCleared() {
		super.onCleared()
		clearInteractors()
	}

}