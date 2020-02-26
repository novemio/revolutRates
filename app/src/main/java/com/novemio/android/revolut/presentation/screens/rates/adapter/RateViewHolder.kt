package com.novemio.android.revolut.presentation.screens.rates.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.novem.lib.core.presentation.BaseViewHolder
import com.novem.lib.core.utils.isNotNullOrEmpty
import com.novemio.android.revolut.domain.currency.model.RATE_NOTHING
import com.novemio.android.revolut.domain.currency.model.Rate
import com.novemio.android.revolut.presentation.screens.rates.ResourceUtils
import kotlinx.android.synthetic.main.list_item_rates.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class RateViewHolder(itemView: View, private var baseRate: Rate) : BaseViewHolder<Rate>(itemView),
    BaseRateObserver {
    lateinit var rate: Rate
    lateinit var onRateClick: ((Rate, currentValue: Double) -> Unit)
    private val symbols = DecimalFormatSymbols.getInstance(Locale.UK)
    private val df: DecimalFormat = DecimalFormat("#,###.##", symbols)

    init {

        itemView.etValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                notifyBaseRateChanged()
            }
        }
        itemView.setOnClickListener {
            notifyBaseRateChanged()
        }
    }

    override fun bind(item: Rate) {
        this.rate = item
        with(itemView) {
            tvTitle.text = item.currency
            tvSubtitle.text = ResourceUtils.getCurrencyMap()[item.currency]
            ivIcon.loadImageFromRate(item)
            setRateValue(item.value)
        }
    }

    fun setRateValue(value: Double) {
        rate.value = value
        if (baseRate.currency != rate.currency) {
            updateEditText(baseRate.toCurrencyValue(rate.value))
        } else {
            updateEditText(baseRate.toCurrencyValue())

        }
    }

    override fun onBaseRateChanged(newRate: Rate) {
        baseRate = newRate
        if (baseRate.currency != rate.currency) {
            updateEditText(baseRate.toCurrencyValue(rate.value))
        }
    }


    private fun notifyBaseRateChanged() {
        if (adapterPosition != 0) {
            baseRate = rate
            itemView.etValue.requestFocus()
            onRateClick.invoke(rate, getCurrentValue())
        }
    }

    private fun getCurrentValue() = with(itemView.etValue.text) {
        this.fromDecimalToDouble()
    }

    private fun updateEditText(value: String) {
        itemView.etValue.setText(value)
    }


    private fun Rate.toCurrencyValue(multiplier: Double = 1.0): String {
        return if (this.value == RATE_NOTHING) "" else df.format(this.value * multiplier).toString()
    }

    @SuppressLint("DefaultLocale")
    private fun Rate.toCountryFlagUrl(): Uri? {
        val code = this.currency.toLowerCase().subSequence(0, 2)
        val path = "file:///android_asset/flags/$code.png"
        return Uri.parse(path)
    }

    private fun ImageView.loadImageFromRate(rate: Rate) {
        Glide.with(this).load(rate.toCountryFlagUrl()).into(this)
    }

}


fun Editable?.fromDecimalToDouble(): Double =
    if (this.isNotNullOrEmpty()) {
        NumberFormat.getNumberInstance(Locale.UK).apply { maximumFractionDigits = 2 }
            .parse(this.toString())
            .toDouble()
    } else {
        RATE_NOTHING
    }

