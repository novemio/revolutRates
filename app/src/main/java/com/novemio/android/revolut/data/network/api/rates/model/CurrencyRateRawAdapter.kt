package com.novemio.android.revolut.data.network.api.rates.model

import com.squareup.moshi.*

private const val KEY_BASE = "baseCurrency"
private const val KEY_RATES = "rates"

class CurrencyRateRawAdapter : JsonAdapter<CurrencyRateRaw>() {

    val moshi = Moshi.Builder().build()

    private val options: JsonReader.Options = JsonReader.Options.of(KEY_BASE, KEY_RATES)

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), KEY_BASE)




    @FromJson
    override fun fromJson(reader: JsonReader): CurrencyRateRaw {
        var baseCurrency: String? = null
        var ratesList: List<RateRaw>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> baseCurrency = stringAdapter.fromJson(reader)
                    ?: throw JsonDataException("Non-null value 'baseCurrency' was null at ${reader.path}")
                1 -> {
                    val ratesObject = reader.readJsonValue() as Map<String,Double>
                    ratesList = mutableListOf()
                    ratesObject.entries.forEach {
                        ratesList.add(RateRaw(it.key, it.value))
                    }
                }
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return CurrencyRateRaw(
            baseCurrency = baseCurrency
                ?: throw JsonDataException("Required property 'baseCurrency' missing at ${reader.path}"),
            ratesList = ratesList
                ?: throw JsonDataException("Required property 'ratesList' missing at ${reader.path}")
        )
    }

    override fun toJson(writer: JsonWriter, value: CurrencyRateRaw?) {

    }


}