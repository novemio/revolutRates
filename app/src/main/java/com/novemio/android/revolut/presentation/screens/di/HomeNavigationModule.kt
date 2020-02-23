package com.novemio.android.revolut.presentation.screens.di

import android.app.Activity
import com.novem.lib.core.di.routes.*
import com.novemio.android.revolut.presentation.screens.HomeActivity
import com.novemio.android.revolut.presentation.screens.HomeNavigationController
import com.novemio.android.revolut.presentation.screens.converter.ConverterFragment
import com.novemio.android.revolut.presentation.screens.converter.ConverterRoutes
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeNavigationModule {

    @Binds
    internal abstract fun bindActivity(homeActivity: HomeActivity): Activity

    @Binds
    internal abstract fun bindFactory(routesModelFactory: RoutesFactory): IRoutesFactory

    @Binds
    internal abstract fun bindNavigatiobCOntroler(routesModelFactory: HomeNavigationController): NavigationController


    @Binds
    @IntoMap
    @RouteKey(ConverterFragment::class)
    internal abstract fun bindConverterRoutes(route: ConverterRoutes): Routes

}