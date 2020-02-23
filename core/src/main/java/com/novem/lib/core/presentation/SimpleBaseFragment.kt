package com.novem.lib.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.novem.lib.core.AndroidComponent
import dagger.android.support.DaggerFragment

private const val TAG: String = "SimpleBaseFragment"

abstract class SimpleBaseFragment
	: DaggerFragment(), AndroidComponent {

	protected var actionBar: ActionBar? = null
		private set

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	) = inflater.inflate(getLayoutId(), container, false).also {
		setHasOptionsMenu(true)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView()

	}

	abstract fun initView()

	@LayoutRes
	abstract fun getLayoutId(): Int

	protected fun setToolbar(
		toolbar: Toolbar, up: Boolean = false, @DrawableRes homeIndicatorIcon: Int? = null
	) {
		setToolbar(toolbar, null, up, homeIndicatorIcon)
	}

	protected fun setToolbar(
		toolbar: Toolbar,
		@StringRes titleId: Int? = null,
		up: Boolean = false, @DrawableRes
		homeIndicatorIcon: Int? = null
	) {
		(activity as AppCompatActivity).setSupportActionBar(toolbar)

		//		navigation.run {
		//			NavigationUI.setupActionBarWithNavController(activity!! as AppCompatActivity, this.navController)
		//		}
		val supportActionBar = (activity as AppCompatActivity).supportActionBar
		actionBar = supportActionBar?.apply {
			titleId?.let { this.setTitle(it) }
			homeIndicatorIcon?.let {
				setHomeAsUpIndicator(homeIndicatorIcon)
			}
			setDisplayHomeAsUpEnabled(up)
		}

	}

	protected fun setToolbarTitle(title: String) {
		actionBar?.title = title
	}

	protected fun setToolbarTitle(@StringRes titleId: Int) {
		actionBar?.setTitle(titleId)
	}

	protected fun showToast(message: String) =
		Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()

}