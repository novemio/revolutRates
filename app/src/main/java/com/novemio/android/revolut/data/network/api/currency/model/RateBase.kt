package com.novemio.android.revolut.data.network.api.currency.model

import com.squareup.moshi.Json

data class CurrencyRateRaw(
    @field:Json(name = "base") val baseCurrency: String?,
    @field:Json(name = "date") val date: String?,
    @field:Json(name = "rates") var ratesList: List<RateRaw>
)

data class RateRaw(
    val currency: String,
    var rate: Double
)


