package com.novem.lib.core.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

open class ObservableFiredEvent<T> : ObservableSingleEvent<T>() {

	override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
		super.observe(owner, Observer { t ->
			if (t != null) {
				observer.onChanged(t)
				postValue(null)
			}
		})
	}
}


