package com.novemio.android.revolut.presentation.screens

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.novem.lib.core.di.routes.NavigationController
import com.novemio.android.revolut.R
import javax.inject.Inject

class HomeNavigationController @Inject constructor(activity: Activity) : NavigationController {

    override val route: NavController by lazy {
        Navigation.findNavController(activity, R.id.homeNavHost)
    }
}