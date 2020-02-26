package com.novemio.android.revolut.presentation.screens.di

import androidx.lifecycle.ViewModel
import com.novem.lib.core.di.viewmodel.IViewModelFactory
import com.novem.lib.core.di.viewmodel.ViewModelFactory
import com.novem.lib.core.di.viewmodel.ViewModelKey
import com.novemio.android.revolut.presentation.screens.HomeActivity
import com.novemio.android.revolut.presentation.screens.HomeViewModel
import com.novemio.android.revolut.presentation.screens.rates.RatesFragment
import com.novemio.android.revolut.presentation.screens.rates.RatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeActivityViewModelModule {


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): IViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivity::class, HomeViewModel::class)
    internal abstract fun bindHomeViewModel(permissionViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RatesFragment::class, RatesViewModel::class)
    internal abstract fun bindPermissionViewModel(permissionViewModel: RatesViewModel): ViewModel
}