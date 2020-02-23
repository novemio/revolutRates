package com.novem.lib.core.presentation.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

private val TAG by lazy { ObservableScreenState::class.java.simpleName }

typealias ImmutableEvent<T> = LiveData<T>

open class ObservableSingleEvent<T> : MutableLiveData<T>() {

	protected val mPending = AtomicBoolean(false)

	@MainThread
	override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

		if (hasActiveObservers()) {
			Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
		}
		super.observe(owner, Observer<T> {
			if (mPending.compareAndSet(true, false)) {
				observer.onChanged(it)
			}
		})
	}

	@MainThread
	override fun setValue(t: T?) {
		mPending.set(true)
		super.setValue(t)
	}

	override fun postValue(t: T?) {
		mPending.set(true)
		super.postValue(t)
	}

}

fun <T> ObservableSingleEvent<T>.toImmutable() = this as ImmutableEvent<T>


