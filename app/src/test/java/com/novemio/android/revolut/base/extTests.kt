package com.dropit.ambassador.android.utils

/**
 * Created by novemio on 7/17/19.
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import org.junit.Assert

inline fun <reified T> assertIsEquals(expected: Any) {
	val value = expected is T
	Assert.assertEquals(value, true)
}

/**
 * Will create new [ViewModelStore], add view model into it using [ViewModelProvider]
 * and then call [ViewModelStore.clear], that will cause [ViewModel.onCleared] to be called
 */
fun ViewModel.callOnCleared() {
	val viewModelStore = ViewModelStore()
	val viewModelProvider = ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
		
		@Suppress("UNCHECKED_CAST")
		override fun <T : ViewModel?> create(modelClass: Class<T>): T = this@callOnCleared as T
	})
	viewModelProvider.get(this@callOnCleared::class.java)
	
	//Run 2
	viewModelStore.clear()//To call clear() in ViewModel
}

