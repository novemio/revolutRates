package com.novemio.android.revolut.presentation.screens.rates.adapter

import java.text.NumberFormat
import java.util.Locale

/**
 * Created by novemio on 2/24/20.
 */

fun main() {
	println(NumberFormat.getNumberInstance(Locale.US).apply { maximumFractionDigits=2 }.format("0.635"))
	println(NumberFormat.getNumberInstance(Locale.US).apply { maximumFractionDigits=2 }.format(1000.4544444448754))
	println(NumberFormat.getNumberInstance(Locale.US).apply { maximumFractionDigits=2 }.format(100000.454557))
	println(NumberFormat.getNumberInstance(Locale.US).apply { maximumFractionDigits=2 }.format(10.4589))
	println(NumberFormat.getNumberInstance(Locale.GERMAN).apply { maximumFractionDigits=2 }.parse("10.4589").toDouble())
	
	
}
