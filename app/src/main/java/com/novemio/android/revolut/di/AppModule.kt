package com.novemio.android.revolut.di

import android.app.Application
import android.content.Context
import com.novemio.android.revolut.RevolutApplication
import com.novemio.android.revolut.data.local.LocalModule
import com.novemio.android.revolut.data.network.NetworkModule
import com.novemio.android.revolut.data.repository.RepoModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        RepoModule::class,
        LocalModule::class
    ]
)

class AppModule {

    @Singleton
    @Provides
    fun provideApplication(application: RevolutApplication): Application {
        return application
    }
    @Provides
    fun provideContext(application: RevolutApplication): Context =
        application.applicationContext
}