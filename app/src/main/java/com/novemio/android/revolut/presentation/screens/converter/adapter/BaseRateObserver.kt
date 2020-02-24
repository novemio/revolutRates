package com.novemio.android.revolut.presentation.screens.converter.adapter

import com.novemio.android.revolut.domain.currency.model.Rate
import java.util.*

interface BaseRateObserver : Observer {


    override fun update(o: Observable?, arg: Any?) {
        (arg as? Rate)?.let {
            onBaseRateChanged(it)
        }
    }
    
    fun onBaseRateChanged(rate: Rate)
    
}
