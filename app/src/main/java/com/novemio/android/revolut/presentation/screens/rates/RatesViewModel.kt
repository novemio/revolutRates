package com.novemio.android.revolut.presentation.screens.rates

import androidx.annotation.VisibleForTesting
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.github.ajalt.timberkt.Timber
import com.novem.lib.core.presentation.CoreViewModel
import com.novem.lib.core.presentation.viewmodel.ObservableScreenState
import com.novem.lib.core.presentation.viewmodel.ScreenState
import com.novemio.android.revolut.data.network.ConnectionManager
import com.novemio.android.revolut.domain.currency.interactor.ObserveCurrencyRate
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import com.novemio.android.revolut.presentation.base.widget.NoConnectionView
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

private const val PERIOD = 1L
private const val BASE_CURRENCY = "EUR"

class RatesViewModel @Inject constructor(
	private val observeCurrencyRate: ObserveCurrencyRate,
	connectionManager: ConnectionManager

) : CoreViewModel(), LifecycleObserver {
	
	var baseRate: Rate = Rate(BASE_CURRENCY, RATE_NOTHING)
	val screenState = ObservableScreenState<RatesState>(true)
	
	@VisibleForTesting
	internal var connectionDisposable: Disposable = connectionManager.observable()
		.skip(1)
		.subscribeBy {
			Timber.d {"Network update $it"}
			if (it.not()) {
				screenState.updateValue(RatesState.NoConnection)
			} else {
				observeCurrencyRate(baseRate.currency, PERIOD)
			}
		}
	
	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	fun onStart() {
		observeCurrencyRate(baseRate.currency, PERIOD)
	}
	
	@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	fun onStop() {
		observeCurrencyRate.clearDisposables()
	}
	
	private fun observeCurrencyRate(currency: String, period: Long = 1) {
		observeCurrencyRate.executeBy(ObserveCurrencyRate.Params(currency, period)) {
			screenState.updateValue(it.toRateState(baseRate))
		}
	}
	
	fun changeBaseCurrency(rate: Rate, value: Double) {
		baseRate = rate.apply { rate.value = value }
		observeCurrencyRate(rate.currency, PERIOD)
	}
	
	override fun onCleared() {
		super.onCleared()
		connectionDisposable.dispose()
	}
	
}

@BindingAdapter("ratesState")
fun NoConnectionView.bindRateState(screnStateRates: ObservableScreenState<RatesState>) {
	screnStateRates.value?.let {
		show(it is ScreenState.Render && it.renderState is RatesState.NoConnection)
	}
	
}


