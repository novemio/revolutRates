package com.novemio.android.revolut.presentation.screens.converter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreViewModel
import com.novem.lib.core.presentation.viewmodel.ObservableScreenState
import com.novem.lib.core.presentation.viewmodel.SimpleState
import com.novemio.android.revolut.domain.currency.interactor.ObserveCurrencyRate
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import javax.inject.Inject


const val PERIOD = 150L

private val TAG by lazy { ConverterViewModel::class.java.simpleName }

class ConverterViewModel @Inject constructor(
    private val observeCurrencyRate: ObserveCurrencyRate

) : CoreViewModel() {


    var baseRate: Rate = Rate("EUR", RATE_NOTHING)
    val screenState = ObservableScreenState<SimpleState<String>>()

    val data = MutableLiveData<List<Rate>>()

    init {

        observeCurrencyRate("EUR", PERIOD)
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


