package com.novemio.android.revolut.presentation.screens.converter


import androidx.navigation.NavController
import com.novem.lib.core.di.routes.NavigationController
import com.novem.lib.core.di.routes.Routes
import javax.inject.Inject

class ConverterRoutes @Inject constructor(

    private val rootNavigation: NavigationController

) : Routes {

    override val navController: NavController by lazy { rootNavigation.route }


}
