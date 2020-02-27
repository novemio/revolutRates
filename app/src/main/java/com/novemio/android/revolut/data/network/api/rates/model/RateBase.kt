package com.novemio.android.revolut.data.network.api.rates.model

import com.novem.lib.core.utils.mapTo
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import com.novemio.android.revolut.domain.currency.model.Rate
import com.squareup.moshi.*

data class CurrencyRateRaw(
    @field:Json(name = "baseCurrency") val baseCurrency: String,
    @field:Json(name = "rates") var ratesList: List<RateRaw>
)

data class RateRaw(
    val currency: String,
    var rate: Double
)


fun CurrencyRateRaw.toDomain() = CurrencyRates(baseCurrency, ratesList.mapTo { it.toDomain() })

fun RateRaw.toDomain() = Rate(currency, rate)



