package com.novemio.android.revolut.domain.currency.interactor

import com.novem.lib.core.utils.result.RequestError.NoInternetError
import com.novem.lib.core.utils.result.SealedResult
import com.novemio.android.revolut.base.TestUtils
import com.novemio.android.revolut.data.network.api.rates.model.CurrencyRateRaw
import com.novemio.android.revolut.data.network.api.rates.model.toDomain
import com.novemio.android.revolut.domain.currency.RatesRepository
import com.novemio.android.revolut.domain.currency.model.CurrencyRates
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.net.UnknownHostException

/**
 * Created by novemio on 2/26/20.
 */

// region constants -------------------------------------------------------------------------------
private const val BASE_CURRENCY = "EUR"
private const val PERIOD = 1L
// endregion constants ----------------------------------------------------------------------------

@ExtendWith(MockKExtension::class)
class ObserveCurrencyRateTest {
	
	// region helper fields ---------------------------------------------------------------------------
	
	private var mockRatesRepository: RatesRepository = mockk()
	val responseEur: CurrencyRates =
		TestUtils.loadJson("mock/rates/ratesByEUR.json", CurrencyRateRaw::class).toDomain()
	
	private val params = ObserveCurrencyRate.Params(BASE_CURRENCY, PERIOD)
	private val mockCallFunction: (SealedResult<CurrencyRates>) -> Unit = mockk()
	private val mockSuccessUnit: SealedResult<CurrencyRates> = SealedResult.OnSuccess(responseEur)
	
	// endregion helper fields ------------------------------------------------------------------------
	
	@Suppress(names = ["PropertyName", "VariableNaming"])
	@SpyK
	internal var SUT = ObserveCurrencyRate(mockRatesRepository)
	
	@BeforeEach
	fun setUp() {
		clearMocks(mockCallFunction, mockRatesRepository)
	}
	
	@Nested
	@DisplayName("Observe currency rates")
	inner class ObserveCurrencyValues {
		
		@Test
		fun `verify params and method calls order`() {
			//stub
			every { mockRatesRepository.getCurrencyRate(BASE_CURRENCY) } returns Single.just(responseEur)
			//call
			SUT.execute(params, mockCallFunction)
			
			//verify
			verifyOrder {
				SUT.clearDisposables()
				mockRatesRepository.getCurrencyRate(BASE_CURRENCY)
				mockCallFunction.invoke(mockSuccessUnit)
			}
			confirmVerified(mockRatesRepository, mockCallFunction)
		}
		
		@Test
		fun `verify no internet connection error`() {
			//stub
			
			every { mockRatesRepository.getCurrencyRate(BASE_CURRENCY) } returns Single.error(UnknownHostException())
			
			SUT.execute(params, mockCallFunction)
			
			//verify
			verify {
				mockCallFunction.invoke(match { it is SealedResult.OnError && it.error is NoInternetError })
			}
			confirmVerified(mockCallFunction)
		}
	}
	
	// region helper methods --------------------------------------------------------------------------
	
	// endregion helper methods -----------------------------------------------------------------------
	
	// region helper classes --------------------------------------------------------------------------
	
	// endregion helper classes -----------------------------------------------------------------------
}