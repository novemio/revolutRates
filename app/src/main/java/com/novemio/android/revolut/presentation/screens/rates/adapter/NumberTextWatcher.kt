package com.novemio.android.revolut.presentation.screens.rates.adapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

private const val DOUBLE_INTEGER_DIGITS = 309
private const val MAX_INT_DIGITS = 7

class NumberTextWatcher(
    private val et: EditText,
    private val afterTextChanged: (Double) -> Unit
) : TextWatcher {

    private val currencyFormatter = CurrencyFormatter()


    override fun afterTextChanged(editable: Editable) {
        if (!et.hasFocus()) return
        val maxIntDig = if (et.hasFocus()) MAX_INT_DIGITS else DOUBLE_INTEGER_DIGITS

        et.removeTextChangedListener(this)
        currencyFormatter.isDecimalSeparatorAlwaysShown(et.hasFocus())
        currencyFormatter.setMaxInegerDigits(maxIntDig)

        val formattedValue = currencyFormatter.format(editable.toString())
        formatAndSelectCursor(formattedValue)

        if (et.hasFocus()) {
            afterTextChanged.invoke(editable.fromDecimalToDouble())
        }
        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        currencyFormatter.checkDecimalSymbol(s.toString())
    }

    private fun formatAndSelectCursor(value: String?) {
        val startLength: Int = et.text.length
        val cp = et.selectionStart
        et.setText(value)
        val endLength: Int = et.text.length
        val sel = cp + (endLength - startLength)
        if (sel > 0 && sel <= et.text.length) {
            et.setSelection(sel)
        } else { // place cursor at the end?
            et.setSelection(et.text.length)
        }
    }

}