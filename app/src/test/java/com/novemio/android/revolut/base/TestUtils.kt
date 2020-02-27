package com.novemio.android.revolut.base


import com.novemio.android.revolut.data.network.api.rates.model.CurrencyRateRawAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.reflect.KClass

object TestUtils {

    private val TEST_MOSHI = initializeMoshi()

    private fun initializeMoshi(): Moshi {
        val builder = Moshi.Builder()
        builder.add(CurrencyRateRawAdapter())
        return builder.build()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> loadJson(path: String, clazz: KClass<T>): T {
        val json = getFileString(path)
        return TEST_MOSHI.adapter(clazz.java).fromJson(json)!!
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> loadJson(path: String, type: Type): T {
        val json = getFileString(path)

        return TEST_MOSHI.adapter<Any>(type).fromJson(json) as T
    }

    private fun getFileString(path: String): String {
        val uri = TestUtils::class.java.classLoader!!.getResource(path).toURI()
        return String(Files.readAllBytes(Paths.get(uri)), Charset.forName("utf-8"))
    }


}


