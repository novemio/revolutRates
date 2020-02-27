package com.novemio.android.revolut.data.repository.rates

import com.novemio.android.revolut.base.RxSchedulersExtension
import com.novemio.android.revolut.base.TestUtils
import com.novemio.android.revolut.data.network.api.rates.RatesApi
import com.novemio.android.revolut.data.network.api.rates.model.CurrencyRateRaw
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verifyAll
import io.reactivex.Single

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by novemio on 2/26/20.
 */

// region constants -------------------------------------------------------------------------------
private const val BASE_CURRENCY = "EUR"
// endregion constants ----------------------------------------------------------------------------

@ExtendWith(MockKExtension::class, RxSchedulersExtension::class)
class RatesRepositoryImplTest {
	
	// region helper fields ---------------------------------------------------------------------------
	
	internal val mockRatesApi: RatesApi = mockk()
	val mockResponseEur: CurrencyRateRaw =
		TestUtils.loadJson("mock/rates/ratesByEUR.json", CurrencyRateRaw::class)
	
	// endregion helper fields ------------------------------------------------------------------------
	
	@Suppress("PropertyName", "VariableNaming")
	lateinit var SUT: RatesRepositoryImpl
	
	@BeforeEach
	fun setUp() {
		SUT = RatesRepositoryImpl(mockRatesApi)
	}
	
	@DisplayName("Get Rates from server")
	@Nested
	inner class GetRates {
		@Test
		fun `validate function calls and arguments`() {
			every { mockRatesApi.getCurrencyRate(BASE_CURRENCY) } returns Single.just(
				mockResponseEur
			)
			
			SUT.getCurrencyRate(BASE_CURRENCY)
				.test()
				.assertComplete()
				.assertNoErrors()
				.dispose()
			
			verifyAll {
				mockRatesApi.getCurrencyRate(BASE_CURRENCY)
			}
			confirmVerified(mockRatesApi)
		}
		
		@Test
		fun `validate success response`() {
			every { mockRatesApi.getCurrencyRate(BASE_CURRENCY) } returns Single.just(mockResponseEur)
			
			SUT.getCurrencyRate(BASE_CURRENCY)
				.test()
				.assertNoErrors()
				.assertValue {
					it.baseCurrency == BASE_CURRENCY && it.rates[0].value == 1.59
				}
				.dispose()
		}
	}
	
	// region helper methods --------------------------------------------------------------------------
	
	// endregion helper methods -----------------------------------------------------------------------
	
	// region helper classes --------------------------------------------------------------------------
	
	// endregion helper classes -----------------------------------------------------------------------
	
}