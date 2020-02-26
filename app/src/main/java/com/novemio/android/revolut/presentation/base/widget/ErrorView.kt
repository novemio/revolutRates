package com.novemio.android.revolut.presentation.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.novem.lib.core.presentation.viewmodel.ImmutableData
import com.novem.lib.core.utils.isNotNullOrEmpty
import com.novem.lib.core.utils.runOnUiThread
import com.novemio.android.revolut.R
import kotlinx.android.synthetic.main.view_error.view.*
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author novemio
 */

private const val DISMISS_DELAY = 2500L

class ErrorView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    init {
        initialize()
        initView()
    }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.view_error, this, true)
    }

    private fun initView() {
        visibility = View.GONE
    }

    fun setOnCloseClick(l: OnCloseClickListener) {
        rvClose.setOnClickListener {
            l.onDismiss(tvError.text.toString())
        }
    }

    interface OnCloseClickListener {
        fun onDismiss(string: String)
    }

    fun show(msg: String) {
        val animate = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply {
            fillAfter = true
        }
        tvError.text = msg
        visibility = View.VISIBLE
        startAnimation(animate)

    }

    fun dismiss() {
        val dismissAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down).apply {
            fillAfter = true
            setAnimationListener(object : AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    visibility = View.GONE
                    tvError.text = ""
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
        }
        startAnimation(dismissAnimation)

    }
}

@BindingAdapter("errorVisibility")
fun errorState(view: ErrorView, errorState: ImmutableData<String>) {
    val show = errorState.value.isNotNullOrEmpty()
    if (!show && view.tvError.text.toString().isEmpty()) return
    if (!show) {
        view.dismiss()
    } else {
        view.show(errorState.value!!)
        Timer().schedule(DISMISS_DELAY) {
            runOnUiThread {
                view.dismiss()
            }
        }
    }
}