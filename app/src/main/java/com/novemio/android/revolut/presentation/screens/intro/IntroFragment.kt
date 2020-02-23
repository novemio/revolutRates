package com.novemio.android.revolut.presentation.screens.intro

import com.novem.lib.core.presentation.SimpleBaseFragment
import com.novemio.android.revolut.R
import kotlinx.android.synthetic.main.fragment_intro.*
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

private const val INTRO = "Powered by Novemio"
private const val DELAY = 70L

class IntroFragment : SimpleBaseFragment() {

	@Inject lateinit var navigation: IntroRoutes

	var index = 0
	override fun getLayoutId(): Int = R.layout.fragment_intro
	override fun initView() {
		tvSplash.text = ""
	}

	override fun onStart() {
		super.onStart()
		animateText(0)
	}
	private fun animateText(delay: Long = 200) {

		Timer().schedule(delay) {
			tvSplash.text = INTRO.subSequence(0, index++)
			if (index <= INTRO.length) {
				animateText(DELAY)
			} else {
				Timer().schedule(2 * DELAY) {
					navigation.toConverter()
				}
			}
		}
	}

}
