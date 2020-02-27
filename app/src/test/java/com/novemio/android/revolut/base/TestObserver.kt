package com.novemio.android.revolut.base

import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {
	private val observedValues: MutableList<T> = mutableListOf()

	override fun onChanged(t: T?) {
		observedValues.add(t!!)
	}

	fun getObservedValues(): List<T> = observedValues

	fun clear() {
		observedValues.clear()
	}

	fun getInitialValue(): T {
		return observedValues[0]
	}

	fun getCurrentValue(): T {
		return observedValues[observedValues.size - 1]
	}

	fun getValueAfter(value: T): T? {
		observedValues.forEachIndexed { index, t ->
			return if (t == value && index + 1 < observedValues.size)
				observedValues[index + 1]
			else null
		}
		return null
	}
}