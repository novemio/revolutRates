package com.novem.lib.core.presentation.viewmodel

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.novem.lib.core.utils.toVisibility

/**
 * Created by novemio on 7/11/19.
 */

@BindingAdapter("screenState")
fun <T> setDataStateForLoading(progressBar: ProgressBar, screenState: ScreenState<T>) {
	progressBar.visibility = (screenState is ScreenState.Loading).toVisibility()
}

@BindingAdapter("screenState")
fun <T> setDataState(view: View, screenState: ScreenState<T>) {
	view.visibility = (screenState is ScreenState.Loading).not().toVisibility()
}

@BindingAdapter("screenState")
fun <T> setDataState(view: SwipeRefreshLayout, screenState: ScreenState<T>) {
	view.isRefreshing = (screenState is ScreenState.Loading)
}



@BindingAdapter("successState")
fun <T> setDataStateForSuccess(view: View, screenState: ScreenState<T>) {
	view.visibility = (screenState is ScreenState.Render<T>).toVisibility()
}
