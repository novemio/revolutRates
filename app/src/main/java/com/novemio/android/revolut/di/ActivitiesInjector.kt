package com.novemio.android.revolut.di

import com.novem.lib.core.di.scopes.ActivityScope
import com.novemio.android.revolut.presentation.screens.HomeActivity
import com.novemio.android.revolut.presentation.screens.di.HomeActivityModule
import com.novemio.android.revolut.presentation.screens.di.HomeActivityFragmentProvider
import com.novemio.android.revolut.presentation.screens.di.HomeNavigationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesInjector {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            HomeActivityFragmentProvider::class,
            HomeNavigationModule::class,
            HomeActivityModule::class
        ]
    )
    abstract fun contributeLoginActivityInjector(): HomeActivity
}