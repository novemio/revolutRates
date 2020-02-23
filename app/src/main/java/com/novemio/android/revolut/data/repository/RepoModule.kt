package com.novemio.android.revolut.data.repository

import com.novemio.android.revolut.data.repository.device.CurrencyRepositoryImpl
import com.novemio.android.revolut.domain.currency.CurrencyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by novemio on 7/7/19.
 */
@Module
abstract class RepoModule {

	@Singleton
	@Binds
	abstract fun provideAuthRepo(deviceRepository: CurrencyRepositoryImpl): CurrencyRepository


}