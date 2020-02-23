package com.novem.lib.core.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by novemio on 7/11/19.
 */
fun View.openKeyboard(context: Context) {
	val inputMethodManager =
		context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard(context: Context) {
	val inputMethodManager =
		context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Context.toggleKeyboard() {
	val inputMethodManager =
		this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

