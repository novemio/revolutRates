package com.novem.lib.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.Arrays

/**
 * Created by novemio on 7/11/19.
 */
fun Array<*>.equalsArray(other: Array<*>) = Arrays.equals(this, other)

fun Array<*>.deepEqualsArray(other: Array<*>) = Arrays.deepEquals(this, other)

inline fun <T> dependantLiveData(
	vararg dependencies: LiveData<out Any>,
	defaultValue: T? = null,
	crossinline mapper: () -> T?
): LiveData<T> =
	MediatorLiveData<T>().also { mediatorLiveData ->
		val observer = Observer<Any> { mediatorLiveData.value = mapper() }
		dependencies.forEach { dependencyLiveData ->
			mediatorLiveData.addSource(dependencyLiveData, observer)
		}
	}.apply { value = defaultValue }

fun String?.isValidEmail(): Boolean {
	return this.isNotNullOrEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String?.isValidPassword(): Boolean {
	return this.isNotNullOrEmpty() && this!!.length >= 8
}

fun Any?.isNotNull() = this != null

fun <T, D> List<T>.mapTo(mapper: (T) -> D): List<D> {
	return map { item -> mapper(item) }
}

fun <T> LiveData<T>.observeBy(@NonNull owner: LifecycleOwner, block: (T) -> Unit) {
	observe(owner, Observer {
		block(it)
	})

}

inline fun Any.runOnUiThread(crossinline func: () -> Unit) {
	Handler(Looper.getMainLooper()).post {
		func.invoke()
	}
}

inline fun <T> Any.onComputationThread(crossinline func: () -> T) =
	Single.fromCallable { func.invoke() }.subscribeOn(Schedulers.io())

inline fun <T> Any.runOnComputationThread(crossinline func: () -> T) =
	Single.fromCallable { func.invoke() }.subscribeOn(Schedulers.io()).subscribe()
