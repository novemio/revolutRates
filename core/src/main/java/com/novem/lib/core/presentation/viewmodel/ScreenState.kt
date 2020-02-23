package com.novem.lib.core.presentation.viewmodel

import com.novem.lib.core.presentation.viewmodel.SimpleState.Success
import com.novem.lib.core.utils.result.SealedResult
import com.novem.lib.core.utils.result.SealedResult.OnError
import com.novem.lib.core.utils.result.SealedResult.OnSuccess

sealed class ScreenState<out T> {
	object Idle : ScreenState<Nothing>()
	object Loading : ScreenState<Nothing>()
	class Render<T>(val renderState: T) : ScreenState<T>()
}

sealed class SimpleState<out T> {
	data class Success<T>(val data: T) : SimpleState<T>()
	data class Error(val msg: String?) : SimpleState<Nothing>()
}

fun <T> SealedResult<T>.toSimpleState(): SimpleState<T> {

	return when (this) {
		is OnSuccess -> Success(data)
		is OnError -> SimpleState.Error(error.message)
	}
}

inline fun <T> ScreenState<SimpleState<T>>.onRenderState(block: (Success<T>) -> Unit) {
	if (this is ScreenState.Render && this.renderState is Success) {
		block.invoke(this.renderState)
	}
}

inline fun ScreenState<*>.onLoading(block: (Boolean) -> Unit) {
	block.invoke(this is ScreenState.Loading)
}

inline fun <reified State, reified ChildState : State> ScreenState<State>.ifOnChildRenderState(
	block: (ChildState) -> Unit
) {
	if (this is ScreenState.Render<State> && this.renderState is ChildState) {
		block.invoke(this.renderState as ChildState)
	}
}

inline fun <reified State> ScreenState<State>.ifOnRenderState(block: (State) -> Unit) {
	if (this is ScreenState.Render<State>) {
		block.invoke(this.renderState)
	}
}

inline fun <State> ScreenState<State>.onRenderStateReturn(block: (State) -> String?): String? {
	return if (this is ScreenState.Render<State>) {
		block(this.renderState)
	} else null
}

