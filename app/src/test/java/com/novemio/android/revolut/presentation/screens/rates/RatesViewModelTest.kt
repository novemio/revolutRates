package com.novemio.android.revolut.presentation.screens.rates

import com.dropit.ambassador.android.utils.InstantTaskExecutorExtension
import com.dropit.ambassador.android.utils.callOnCleared
import com.novem.lib.core.presentation.viewmodel.ScreenState
import com.novem.lib.core.presentation.viewmodel.ScreenState.Loading
import com.novem.lib.core.presentation.viewmodel.ScreenState.Render
import com.novemio.android.revolut.base.RxSchedulersExtension
import com.novemio.android.revolut.base.TestObserver
import com.novemio.android.revolut.data.network.ConnectionManager
import com.novemio.android.revolut.domain.currency.interactor.ObserveCurrencyRate
import com.novemio.android.revolut.domain.currency.model.Rate
import com.novemio.android.revolut.presentation.screens.rates.RatesState.NoConnection
import io.mockk.Runs
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.subjects.PublishSubject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by novemio on 2/27/20.
 */

// region constants -------------------------------------------------------------------------------

// endregion constants ----------------------------------------------------------------------------

@ExtendWith(MockKExtension::class, RxSchedulersExtension::class, InstantTaskExecutorExtension::class)
class RatesViewModelTest {
	
	// region helper fields ---------------------------------------------------------------------------
	private val testObserver: TestObserver<ScreenState<RatesState>> = TestObserver()
	private val mockObserveCurrencyRate: ObserveCurrencyRate = mockk()
	private val mockConnectionManager: ConnectionManager = mockk()
	private lateinit var networkObservable: PublishSubject<Boolean>
	// endregion helper fields ------------------------------------------------------------------------
	
	@Suppress("PropertyName", "VariableNaming")
	internal lateinit var SUT: RatesViewModel
	
	@BeforeEach
	fun setUp() {
		clearMocks(mockObserveCurrencyRate)
		testObserver.clear()
		networkObservable = PublishSubject.create<Boolean>()
		setStub()
		SUT = spyk(RatesViewModel(mockObserveCurrencyRate, mockConnectionManager))
		networkObservable.onNext(true) //this one is skipped on view model
		SUT.screenState.observeForever(testObserver)
	}
	
	@Test
	fun `verify NoConnection state on connection event = false`() {
		//stub
		//		networkObservable.onNext(false)
		networkObservable.onNext(false)
		
		//verify
		with(testObserver.getCurrentValue()) {
			assert(this is Render && this.renderState is NoConnection)
		}
	}
	
	@Test
	fun `verify connection observing on init`() {
		//verify
		verify { mockConnectionManager.observable() }
		confirmVerified(mockConnectionManager)
		
	}
	
	@Test
	fun `verify state loading on init`() {
		//verify
		assert(testObserver.getInitialValue() is Loading)
		
	}
	
	@Test
	fun `verify network call on ON_START event`() {
		//call
		SUT.onStart()
		//verify
		verify {
			mockObserveCurrencyRate.execute(any(), any())
		}
		confirmVerified(mockObserveCurrencyRate)
		
	}
	
	@Test
	fun `verify stop observing on ON_STOP event`() {
		//call
		SUT.onStop()
		//verify
		verify {
			mockObserveCurrencyRate.clearDisposables()
		}
		
	}
	
	@Test
	fun `onCleared() -  observing network event  is disposed`() {
		//call
		SUT.callOnCleared()
		//verify
		assert(SUT.connectionDisposable.isDisposed)
	}
	
	@Test
	fun `changeBaseCurrency() -  verify calls`() {
		SUT.baseRate = Rate("EUR",1.0)
		//stub
		val rate = Rate("USD", 2.6)
		//call
		SUT.changeBaseCurrency(rate, 8.6)
		//verify
		verify {
			mockObserveCurrencyRate.execute(any(), any())
		}
		
		assert(SUT.baseRate.currency == "USD")
		assert(SUT.baseRate.value == 8.6)
		
		
	}
	
	// region helper methods --------------------------------------------------------------------------
	
	private fun setStub() {
		every { mockObserveCurrencyRate.execute(any(), any()) } just Runs
		every { mockObserveCurrencyRate.clearDisposables() } just Runs
		every { mockConnectionManager.observable() } returns networkObservable
		
	}
	
	// endregion helper methods -----------------------------------------------------------------------
	
	// region helper classes --------------------------------------------------------------------------
	
	// endregion helper classes -----------------------------------------------------------------------
}