package com.novemio.android.revolut.presentation.screens.rates

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreViewModel
import com.novem.lib.core.presentation.viewmodel.ObservableScreenState
import com.novem.lib.core.presentation.viewmodel.SimpleState
import com.novemio.android.revolut.domain.currency.interactor.ObserveCurrencyRate
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import javax.inject.Inject


const val PERIOD = 1L

private val TAG by lazy { RatesViewModel::class.java.simpleName }

class RatesViewModel @Inject constructor(
    private val observeCurrencyRate: ObserveCurrencyRate

) : CoreViewModel(),LifecycleObserver {


    var baseRate: Rate = Rate("EUR", RATE_NOTHING)
    val screenState = ObservableScreenState<SimpleState<String>>(true)

    val data = MutableLiveData<List<Rate>>()




    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        observeCurrencyRate(baseRate.currency, PERIOD)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        observeCurrencyRate.clearDisposables()
    }

    private fun observeCurrencyRate(currency: String, period: Long = 1) {
        Log.d(TAG, "observeCurrencyRate: $currency")
        observeCurrencyRate.executeBy(ObserveCurrencyRate.Params(currency, period)) {

            it.onSuccess {
                val list = mutableListOf<Rate>()
                list.add(baseRate)
                list.addAll(it.rates)
                data.postValue(list)
            }

            Timber.d { it.toString() }
        }
    }

    fun changeBaseCurrency(rate: Rate, value: Double) {
        Log.d("ConverterViewModel", "changeBaseCurrency: $rate")
        baseRate = rate.apply { rate.value = value }
        observeCurrencyRate(rate.currency, PERIOD)
    }

}


