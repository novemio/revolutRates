package com.novemio.android.revolut

import android.content.Context
import com.facebook.stetho.Stetho
import com.novem.lib.core.presentation.ViewModelIdProvider
import com.novemio.android.revolut.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class RevolutApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        app = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        setViewModelID()
    }

    private fun setViewModelID() {
        ViewModelIdProvider.viewModelId = BR.vm
    }


    companion object {
        private lateinit var app: RevolutApplication
        fun getInstance() = app
    }

}