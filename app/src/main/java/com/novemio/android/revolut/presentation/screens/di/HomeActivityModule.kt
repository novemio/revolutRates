package com.novemio.android.revolut.presentation.screens.di;

import com.novem.lib.core.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    fun provideTestObject() =
        TEstOBject("Milan")
}


data class TEstOBject @Inject constructor(var name: String) {

}