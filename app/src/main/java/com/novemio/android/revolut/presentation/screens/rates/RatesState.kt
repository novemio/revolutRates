package com.novemio.android.revolut.presentation.screens.rates

import com.novem.lib.core.presentation.viewmodel.ObservableScreenState
import com.novem.lib.core.presentation.viewmodel.ScreenState
import com.novem.lib.core.utils.result.RequestError
import com.novem.lib.core.utils.result.SealedResult
import com.novem.lib.core.utils.toVisibility
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import com.novemio.android.revolut.domain.currency.model.Rate

/**
 * Created by novemio on 2/26/20.
 */
sealed class RatesState {

    data class OnSuccess(val data: List<Rate>) : RatesState()
    object NoConnection : RatesState()
    object NoItems : RatesState()
    data class RateError(val msg: String) : RatesState()
}


fun SealedResult<CurrencyRates>.toRateState(baseRate: Rate): RatesState {

    return when (this) {
        is SealedResult.OnSuccess -> when {
            data.rates.isNotEmpty() -> RatesState.OnSuccess(data.toRates(baseRate))
            else -> RatesState.NoItems

        }
        is SealedResult.OnError -> when (error) {
            is RequestError.NoInternetError -> RatesState.NoConnection
            else -> RatesState.RateError(error.message ?: "Unknown Error")
        }
    }
}

private fun CurrencyRates.toRates(baseRate: Rate): MutableList<Rate> {
    val list = mutableListOf<Rate>()
    list.add(baseRate)
    list.addAll(this.rates)
    return list
}


object RateBinding {

    @JvmStatic
    fun setConnection(screenState: ObservableScreenState<RatesState>): Int {
        screenState.value.let {
            return (it is ScreenState.Render && it.renderState is RatesState.NoConnection).toVisibility()
        }
    }


}


