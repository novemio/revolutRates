package com.novemio.android.revolut.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager @Inject constructor(private val context: Context) {

    /**
     * Helper method for checking if device is connected to the internet
     */

    private val observable = ReactiveNetwork
        .observeNetworkConnectivity(context)
        .flatMapSingle { connectivity -> ReactiveNetwork.checkInternetConnectivity() }
        .subscribeOn(Schedulers.io())

    val isNetworkAvailable: Boolean
        get() {
            return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {
                val activeNetwork = it.activeNetworkInfo
                activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
        }

    fun subscribeOnNetwork(onEvent: (Boolean) -> Unit): Disposable {
        return observable.subscribe {
            onEvent.invoke(it)
        }
    }

    fun observable()=observable

}