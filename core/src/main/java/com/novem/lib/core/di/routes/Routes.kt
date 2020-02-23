package com.novem.lib.core.di.routes

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.novem.lib.core.R

interface Routes {
	val navController: NavController
}

fun NavController.navigateWithSlideAnimation(navDirections: NavDirections) {
	this.navigate(navDirections, getSlidingNavOptionBuilder().build())
}

fun NavController.navigateWithSlideAnimation(@IdRes actionId: Int) {
	this.navigate(actionId, null, getSlidingNavOptionBuilder().build())
}

fun getSlidingNavOptionBuilder() = NavOptions.Builder()
	.setEnterAnim(R.anim.slide_in_right)
	.setExitAnim(R.anim.slide_out_left)
	.setPopEnterAnim(R.anim.slide_in_left)
	.setPopExitAnim(R.anim.slide_out_right)


fun getDefNavOptionBuilder() = NavOptions.Builder()
	.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
	.setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
	.setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
	.setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
	




