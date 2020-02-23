package com.novemio.android.revolut.presentation.screens.intro

import androidx.navigation.NavController
import com.novem.lib.core.di.routes.NavigationController
import com.novem.lib.core.di.routes.Routes
import com.novemio.android.revolut.R
import com.novemio.android.revolut.presentation.screens.HomeNavigationController
import javax.inject.Inject

/**
 * Created by novemio on 12/10/2019.
 */

class IntroRoutes @Inject constructor(
    private val rootNavigation: NavigationController
) : Routes {


    override val navController: NavController by lazy { rootNavigation.route }

    fun toConverter() = navController.navigate(R.id.action_introFragment_to_converterFragment)

}