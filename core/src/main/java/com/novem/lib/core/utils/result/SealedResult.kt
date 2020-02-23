package com.novem.lib.core.utils.result

sealed class SealedResult<out T> {

	data class OnSuccess<out T>(val data: T) : SealedResult<T>()
	data class OnError<out T>(val error: RequestError) : SealedResult<T>()

	fun <M> map(block: (T) -> M): SealedResult<M> {
		return if (this is OnSuccess) OnSuccess(block.invoke(this.data))
		else OnError((this as OnError).error)
	}

	fun onSuccess(block: (T) -> Unit) {
		if (this is OnSuccess) block(data)
	}
}


