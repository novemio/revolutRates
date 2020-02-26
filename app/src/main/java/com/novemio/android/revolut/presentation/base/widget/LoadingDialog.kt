package com.novemio.android.revolut.presentation.base.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.github.ajalt.timberkt.Timber
import com.novemio.android.revolut.R
import kotlinx.android.synthetic.main.dialog_loading.lvLoading

/**
 * Created by novemio on 2/28/18.
 */

class LoadingDialog constructor(context: Context) : Dialog(context, R.style.AppTheme_Dialog_Loading),
	LoadingView.Listener {
	
	private var showing = false
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_loading)
		setCanceledOnTouchOutside(false)
		setCancelable(false)
		lvLoading.setListener(this)
	}
	
	override fun show() {
		Timber.d { "show: " }
		if (!isShowing) {
			super.show()
			showing = true
			lvLoading.show()
		}
	}
	
	override fun dismiss() {
		Timber.d { "start: " }
		if (showing) {
			Timber.d { "showing hide" }
			lvLoading.hide()
			
		}
	}
	
	override fun onShow() {
	}
	
	override fun onHide() {
		Timber.d { "start " }
		try {
			Timber.d { "dismiss" }
			showing = false
			super.dismiss()
		} catch (e: IllegalArgumentException) {
			Timber.e(e) { "exception: " }
		}
	}
	
}