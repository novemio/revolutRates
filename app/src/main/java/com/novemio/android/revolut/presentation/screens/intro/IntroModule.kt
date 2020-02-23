package com.novemio.android.revolut.presentation.screens.intro;

import com.novem.lib.core.di.routes.NavigationController
import com.novem.lib.core.di.scopes.FragmentScope
import com.novemio.android.revolut.presentation.screens.HomeNavigationController
import dagger.Module
import dagger.Provides

@Module
class IntroModule {

	@Provides
	@FragmentScope
	fun provideRootRoots(rootNavigation: NavigationController) = IntroRoutes(rootNavigation)
}
	