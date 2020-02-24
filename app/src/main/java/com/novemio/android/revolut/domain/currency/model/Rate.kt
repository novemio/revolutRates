package com.novemio.android.revolut.domain.currency.model

/**
 * Created by novemio on 2/23/20.
 */

const val RATE_NOTHING = -1.0

data class Rate(val currency: String, var value: Double) {

}
