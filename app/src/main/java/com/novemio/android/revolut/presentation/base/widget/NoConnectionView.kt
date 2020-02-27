package com.novemio.android.revolut.presentation.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.novem.lib.core.presentation.utils.hideKeyboard
import com.novemio.android.revolut.R
import kotlinx.android.synthetic.main.view_no_connection.view.*

/**
 * Created by novemio on 2/26/20.
 */
class NoConnectionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    private lateinit var animationLottie: Animation

    init {
        initialize()
        initializeAttributes(attrs)
        initState()
    }


    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.view_no_connection, this, true)

    }

    private fun initializeAttributes(attrs: AttributeSet?) {

    }

    private fun initState() {
        visibility = View.GONE
        animationView.cancelAnimation()
    }

    fun show(value: Boolean) {
        if (value) {
            startAnimate()
        } else stopAnimate()
    }

    private fun startAnimate() {
        hideKeyboard()
        visibility = View.VISIBLE
        animationView.playAnimation()
    }

    private fun stopAnimate() {
        visibility = View.GONE
        animationView.cancelAnimation()
    }


}

@BindingAdapter("show")
fun NoConnectionView.bindingShow(show: Boolean) {
    show(show)
}

