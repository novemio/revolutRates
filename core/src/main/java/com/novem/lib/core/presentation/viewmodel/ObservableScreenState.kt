package com.novem.lib.core.presentation.viewmodel

import androidx.lifecycle.LiveData


typealias ImmutableScreenState<T> = LiveData<ScreenState<T>>

class ObservableScreenState<T>(loading: Boolean = false) : ObservableData<ScreenState<T>>() {


	init {
		value = if (loading) ScreenState.Loading else ScreenState.Idle

	}

	fun setLoading() {
		if (value != ScreenState.Loading) postValue(ScreenState.Loading)
	}

	/***
	 * Update observable value
	 * @param result SealedResult<T>
	 */
	fun updateValue(result: T) {
		postValue(ScreenState.Render(result))
	}
}

fun <T> ObservableScreenState<T>.toImmutable() = this as ImmutableScreenState<T>