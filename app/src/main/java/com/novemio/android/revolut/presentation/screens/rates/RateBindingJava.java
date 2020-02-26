package com.novemio.android.revolut.presentation.screens.rates;

import com.novem.lib.core.presentation.viewmodel.ObservableScreenState;
import com.novem.lib.core.presentation.viewmodel.ScreenState;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by novemio on 2/26/20.
 */
public class RateBindingJava {


    public static int setConnection(ObservableScreenState<RatesState> state) {
        ScreenState<? extends RatesState> value = state.getValue();
        if (value instanceof ScreenState.Render && ((ScreenState.Render<? extends RatesState>) value).getRenderState() instanceof RatesState.NoConnection)
            return VISIBLE;
        else return GONE;
    }

}
