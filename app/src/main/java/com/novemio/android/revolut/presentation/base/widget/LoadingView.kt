package com.novemio.android.revolut.presentation.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.utils.hideKeyboard
import com.novem.lib.core.presentation.viewmodel.ScreenState
import com.novemio.android.revolut.R

/**
 * @author xix
 */

class LoadingView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
	FrameLayout(context, attrs) {
	
	internal var mStartTime: Long = -1L
	
	internal var mPostedHide = false
	
	internal var mPostedShow = false
	
	internal var mDismissed = false
	private var listener: Listener? = null
	private var delay= MIN_DELAY
	
	init {
		initialize()
		initializeAttributes(attrs)
	}
	
	private fun initialize() {
		/** Inflate Layout and set Attributes.  */
		LayoutInflater.from(context).inflate(R.layout.view_loading, this, true)
		/** Initialize Views.  */
		isClickable = true
		isFocusable = true
		
	}
	
	private fun initializeAttributes(attrs: AttributeSet?) {
		attrs?.let {
			
			val state = it.getAttributeBooleanValue(R.styleable.LoadingView_loadingState, false)
			delay=it.getAttributeIntValue(R.styleable.LoadingView_loadingDelay, MIN_DELAY)
			if (state) setState(true)
			
		}
	}
	
	 fun setState(show: Boolean) {
		Timber.d { "setState: $show" }
		if (show) show() else hide()
	}
	
	public fun getState() = mPostedShow
	
	private fun hideRun() {
		mPostedHide = false
		mStartTime = -1
		visibility = View.GONE
		if (listener != null) {
			listener!!.onHide()
		}
	}
	
	private fun showRun() {
		mPostedShow = false
		Timber.d { "delay show: " }
		if (!mDismissed) {
			mStartTime = System.currentTimeMillis()
			hideKeyboard()
			visibility = View.VISIBLE
			if (listener != null) {
				listener!!.onShow()
			}
		}
	}
	
	fun setListener(listener: Listener) {
		this.listener = listener
	}
	
	public override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		removeCallbacks()
	}
	
	public override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		removeCallbacks()
	}
	
	private fun removeCallbacks() {
		removeCallbacks(null)
	}
	
	/**
	 * Hide the progress view if it is visible. The progress view will not be
	 * hidden until it has been shown for at least a minimum show time. If the
	 * progress view was not yet visible, cancels showing the progress view.
	 */
	@Synchronized
	fun hide() {
		Timber.d { " hide: " }
		mDismissed = true
		removeCallbacks()
		mPostedShow = false
		
		val diff = System.currentTimeMillis() - mStartTime
		if (diff >= MIN_SHOW_TIME || mStartTime == -1L) {
			// The progress spinner has been shown long enough
			// OR was not shown yet. If it wasn't shown yet,
			// it will just never be shown.
			visibility = View.GONE
			if (listener != null) {
				listener!!.onHide()
			}
		} else {
			// The progress spinner is shown, but not long enough,
			// so put a delayed message in to hide it when its been
			// shown long enough.
			if (!mPostedHide) {
				postDelayed({ this.hideRun() }, MIN_SHOW_TIME - diff)
				mPostedHide = true
			}
		}
	}
	
	/**
	 * Show the progress view after waiting for a minimum delay. If
	 * during that time, hide() is called, the view is never made visible.
	 */
	@Synchronized
	fun show() {
		// Reset the start time.
		hideKeyboard()
		mStartTime = -1
		mDismissed = false
		removeCallbacks()
		mPostedHide = false
		if (!mPostedShow) {
			postDelayed({ this.showRun() }, delay.toLong())
			mPostedShow = true
		}
	}
	
	interface Listener {
		fun onShow()
		
		fun onHide()
	}
	
	companion object {
		
		val IMAGE = 1
		val TEXT = 0
		private val LOG_TAG = LoadingView::class.java.simpleName
		
		private val MIN_SHOW_TIME = 500 // ms
		private val MIN_DELAY = 500 // ms
	}
	//        TypedArray lAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.StatisticView);
	//        try {
	//            setDescription(lAttributes.getString(R.styleable.StatisticView_svDescription));
	//            setValue(lAttributes.getString(R.styleable.StatisticView_svValue));
	//            setIcon(lAttributes.getDrawable(R.styleable.StatisticView_svIcon));
	//            setType(lAttributes.getInt(R.styleable.StatisticView_svType, TEXT));
	//        } finally {
	//            lAttributes.recycle();
	//        }
}

@BindingAdapter("loadingStatus")
fun <T> setDataStateForLoading(loadingView: LoadingView, screenState: ScreenState<T>?) {
	loadingView.setState(screenState is ScreenState.Loading)
}