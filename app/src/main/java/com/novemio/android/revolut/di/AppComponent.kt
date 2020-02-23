package com.novemio.android.revolut.di

import com.novemio.android.revolut.RevolutApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivitiesInjector::class
    ]
)
interface AppComponent : AndroidInjector<RevolutApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<RevolutApplication>

}