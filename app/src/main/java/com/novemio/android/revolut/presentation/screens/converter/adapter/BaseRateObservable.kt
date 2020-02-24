package com.novemio.android.revolut.presentation.screens.converter.adapter

import android.util.Log
import com.novemio.android.revolut.domain.currency.model.Rate
import java.util.*


class BaseRateObservable(private var baseRate: Rate) : Observable() {

    fun getRate(): Rate {
        return baseRate.copy()
    }

    fun updateBaseRate(value: Rate) {
            baseRate = value
            setChanged()
            notifyObservers(baseRate)
    }

    override fun addObserver(observer: Observer) {
        super.addObserver(observer)
        observer.update(this, baseRate)
    }


}
