package com.novemio.android.revolut.presentation.screens.converter

import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreViewModel
import com.novem.lib.core.presentation.viewmodel.ObservableScreenState
import com.novem.lib.core.presentation.viewmodel.SimpleState
import com.novemio.android.revolut.domain.currency.interactor.GetCurrencyRate
import com.novemio.android.revolut.domain.currency.interactor.ObserveCurrencyRate
import com.novemio.android.revolut.presentation.screens.di.TEstOBject
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.concurrent.schedule

class ConverterViewModel @Inject constructor(
    val tEstOBject: TEstOBject,
    private val observeCurrencyRate: ObserveCurrencyRate

) : CoreViewModel() {


    val screenState = ObservableScreenState<SimpleState<String>>()


    init {


        Timer().schedule(23000) {
            observeCurrnecyRate("USD", 5)
        }


        observeCurrnecyRate("EUR", 5)
    }

    private fun observeCurrnecyRate(currency: String, period: Long) {
        observeCurrencyRate.executeBy(ObserveCurrencyRate.Params(currency, period)) {
            Timber.d { it.toString() }
        }
    }

}


