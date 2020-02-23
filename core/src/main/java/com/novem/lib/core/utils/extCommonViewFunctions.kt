package com.novem.lib.core.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun EditText.asString() =
	this.text.toString()

fun Date.asString(pattern: String = "dd. MMM yyyy", locale: Locale = Locale.getDefault()): String =
	SimpleDateFormat(pattern, locale).format(this)

fun Fragment.showToast(message: String) =
	Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()

fun ViewGroup.setGoneVisibility(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.GONE

fun ViewGroup.setHiddenVisibility(visibility: Boolean) =
	if (visibility) this.visibility = View.VISIBLE else this.visibility = View.INVISIBLE

fun Float.asString() =
	String.format("%.2f", this) + "$"

fun String.asDate(pattern: String = "dd. MMM yyyy", locale: Locale = Locale.getDefault()): Date =
	SimpleDateFormat(pattern, locale).parse(this)

fun String.compressBase64(): String {
	Base64.decode(this.toByteArray(), Base64.DEFAULT).also {
		BitmapFactory.decodeByteArray(
			it,
			0,
			it.size,
			BitmapFactory.Options().apply { inPurgeable = true }).run {
			if (height <= 200 && width <= 200) return this@compressBase64
			Bitmap.createScaledBitmap(this, 200, 200, false)
			ByteArrayOutputStream().also { stream ->
				compress(Bitmap.CompressFormat.PNG, 100, stream)
				return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
			}
		}
	}
}

fun View.hideKeyboard() {
	val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.hideSoftInputFromWindow(this.windowToken, 0)
	clearFocus()
}

fun Boolean.toVisibility() = if (this) View.VISIBLE else View.GONE

fun CharSequence?.isNotNullOrEmpty(): Boolean = isNullOrEmpty().not()

fun ImageView.loadImage(url: String, @DrawableRes placeholderId: Int = -1) {
	Glide.with(this).load(url)
		.apply {
			if (placeholderId != -1) {
				placeholder(placeholderId)
			}
		}
		.into(this)

}

fun ImageView.loadCircleImage(url: String, @DrawableRes placeholderId: Int = -1) {
	Glide.with(this).load(url)
		.apply {
			if (placeholderId != -1) {
				placeholder(placeholderId)
			}
		}
		.apply(RequestOptions.circleCropTransform())
		.into(this)

}



