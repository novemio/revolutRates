package com.novemio.android.revolut.presentation.screens.rates


import androidx.navigation.NavController
import com.novem.lib.core.di.routes.NavigationController
import com.novem.lib.core.di.routes.Routes
import javax.inject.Inject

class RatesRoutes @Inject constructor(

    private val rootNavigation: NavigationController

) : Routes {

    override val navController: NavController by lazy { rootNavigation.route }


}
