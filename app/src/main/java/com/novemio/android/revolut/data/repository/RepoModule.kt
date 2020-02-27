package com.novemio.android.revolut.data.repository

import com.novemio.android.revolut.data.repository.rates.RatesRepositoryImpl
import com.novemio.android.revolut.domain.currency.RatesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepoModule {

	@Singleton
	@Binds
	abstract fun provideRatesRepo(deviceRepository: RatesRepositoryImpl): RatesRepository


}