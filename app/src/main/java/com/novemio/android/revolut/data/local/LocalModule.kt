package com.novemio.android.revolut.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.novemio.android.revolut.data.local.authorization.AuthorizationCache
import com.novemio.android.revolut.data.local.authorization.AuthorizationCacheImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

private const val SHARED_PREF_AUTH = "Authentication"
private const val SHARED_PREF_USER = "USER"

@Module
class LocalModule {

    @Singleton
    @Provides
    @Named(SHARED_PREF_AUTH)
    fun provideSharedPreference(
        application: Application
    ): SharedPreferences = application.getSharedPreferences(SHARED_PREF_AUTH, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    @Named(SHARED_PREF_USER)
    fun provideSharedPreferenceUser(
        application: Application
    ): SharedPreferences = application.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAuthCache(@Named(SHARED_PREF_AUTH) sharedPreferences: SharedPreferences): AuthorizationCache =
        AuthorizationCacheImpl(sharedPreferences)


}