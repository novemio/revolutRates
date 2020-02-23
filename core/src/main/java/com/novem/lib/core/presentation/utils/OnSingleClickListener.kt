package com.novem.lib.core.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter
import java.util.concurrent.atomic.AtomicBoolean

class OnSingleClickListener(
	private val clickListener: View.OnClickListener,
	private val intervalMs: Long = 1000
) : View.OnClickListener {
	private var canClick = AtomicBoolean(true)

	override fun onClick(view: View) {
		if (canClick.getAndSet(false)) {
			view.run {
				postDelayed({
					canClick.set(true)
				}, intervalMs)
				clickListener.onClick(view)
			}
		}
	}
}

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener) {
	clickListener.also {
		setOnClickListener(OnSingleClickListener(it))
	}
}
