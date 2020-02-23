package com.novem.lib.core.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias ImmutableData<T> = LiveData<T>

open class ObservableData<T> : MutableLiveData<T>()

fun <T> MutableLiveData<List<T>>.add(item: T, position: Int? = null) {
	var updatedItems = this.value as ArrayList<T>?
	if (updatedItems == null) updatedItems = arrayListOf()
	if (position != null) updatedItems.add(position, item) else updatedItems.add(item)
	this.postValue(updatedItems)
}

fun <T> MutableLiveData<List<T>>.remove(item: T) {
	val updatedItems = this.value as ArrayList
	updatedItems.remove(item).let {
		if (it) postValue(updatedItems) else Log.d("MutableLiveData<List>", "item not removed")
	}
}