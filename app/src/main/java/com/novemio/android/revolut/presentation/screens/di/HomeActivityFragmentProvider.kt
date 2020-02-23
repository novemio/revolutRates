package com.novemio.android.revolut.presentation.screens.di

import com.novem.lib.core.di.scopes.FragmentScope
import com.novemio.android.revolut.presentation.screens.converter.ConverterFragment
import com.novemio.android.revolut.presentation.screens.intro.IntroFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [HomeActivityViewModelModule::class])
abstract class HomeActivityFragmentProvider {



    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentInjector(): IntroFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeConverterFragmentInjector(): ConverterFragment

}