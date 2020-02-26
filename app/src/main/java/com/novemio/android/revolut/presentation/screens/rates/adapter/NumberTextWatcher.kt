package com.novemio.android.revolut.presentation.screens.rates.adapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.util.*

private val TAG by lazy { NumberTextWatcher::class.java.simpleName }

private const val DOUBLE_INTEGER_DIGITS = 309

class NumberTextWatcher(
    private val et: EditText,
    private val afterTextChanged: (Double) -> Unit
) : TextWatcher {

    private val symbols = DecimalFormatSymbols.getInstance(Locale.UK)
    private val df: DecimalFormat = DecimalFormat("#,###.##", symbols)
    private val df0: DecimalFormat = DecimalFormat("#,##0.0;#,###.0#", symbols)
    private val df00: DecimalFormat = DecimalFormat("#,##0.00;#,###.00", symbols)
    private val dfnd: DecimalFormat = DecimalFormat("#,###", symbols)
    private var hasFractionalPart: Boolean
    private var hasFraction0 = false
    private var hasFRaction00 = false

    init {

        hasFractionalPart = false
    }

    override fun afterTextChanged(editable: Editable) {
        if (editable.isEmpty()) return
        et.removeTextChangedListener(this)
        df.isDecimalSeparatorAlwaysShown = et.hasFocus()
        val maxIntDig = if (et.hasFocus()) 7 else DOUBLE_INTEGER_DIGITS
        df.maximumIntegerDigits = maxIntDig
        df0.maximumIntegerDigits = maxIntDig
        df00.maximumIntegerDigits = maxIntDig
        dfnd.maximumIntegerDigits = maxIntDig


        val startLength: Int = et.text.length
        val number: Number
        try {
            val value = editable.toString()
                .replace(df.decimalFormatSymbols.groupingSeparator.toString(), "")
            number = df.parse(value)
        } catch (nfe: NumberFormatException) {
            return
        } catch (e: ParseException) {
            return
        }
        val cp = et.selectionStart
        val formatedValue = when {
            hasFRaction00 -> df00.format(number)
            hasFraction0 -> df0.format(number)
            hasFractionalPart -> df.format(number)
            else -> dfnd.format(number)
        }

        et.setText(formatedValue)

        val endLength: Int = et.text.length
        val sel = cp + (endLength - startLength)
        if (sel > 0 && sel <= et.text.length) {
            et.setSelection(sel)
        } else { // place cursor at the end?
            et.setSelection(et.text.length)
        }

        if (et.hasFocus()) {
            afterTextChanged.invoke(editable.fromDecimalToDouble())
        }
        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val decimalSeparator = df.decimalFormatSymbols.decimalSeparator.toString()
        val text = s.toString()
        hasFractionalPart = text.contains(decimalSeparator)
        hasFraction0 = text.endsWith("${decimalSeparator}0")
        hasFRaction00 = text.endsWith("${decimalSeparator}00")

    }

}