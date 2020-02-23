package com.novemio.android.revolut.presentation.screens

import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.novemio.android.revolut.presentation.screens.di.TEstOBject
import javax.inject.Inject

class HomeViewModel @Inject constructor(

    val testObject: TEstOBject
) : ViewModel() {
    init {

        Timber.d { ""+testObject.hashCode() }
    }
}