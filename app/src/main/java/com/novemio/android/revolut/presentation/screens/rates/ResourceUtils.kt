package com.novemio.android.revolut.presentation.screens.rates

import com.novemio.android.revolut.R
import com.novemio.android.revolut.RevolutApplication

/**
 * Created by novemio on 2/25/20.
 */
object ResourceUtils {


    fun getCurrencyMap(): HashMap<String, String> {
        val res = RevolutApplication.getInstance().resources
        val ta = res.obtainTypedArray(R.array.currencies_list)
        val n = ta.length();
        val map = HashMap<String, String>()
        for (i in 0 until n) {
            val id = ta.getResourceId(i, 0);
            if (id > 0) {
                val stringArray = res.getStringArray(id)
                map[stringArray[0]] = stringArray[1]
            } else {
                // something wrong with the XML
            }
        }
        ta.recycle()
        return map
    }

}