package com.novemio.android.revolut.presentation

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.widget.TextView
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

fun TextView.setRevolutTextColor() {
    val textShader: Shader = LinearGradient(
        0f,
        0f,
       width.toFloat(),
        height.toFloat(),
        intArrayOf(Color.parseColor("#52C3E7"), Color.parseColor("#0089CC")),
        floatArrayOf(0f, 1f),
        TileMode.CLAMP
    )
    this.paint.shader = textShader
}