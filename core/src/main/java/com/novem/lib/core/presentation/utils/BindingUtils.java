package com.novem.lib.core.presentation.utils;

import android.view.View;

import com.novem.lib.core.presentation.viewmodel.ScreenState;

/**
 * Created by novemio on 2/16/20.
 */
public class BindingUtils {


    public static boolean isNullOEmpty(String value) {
        return value == null || value.equals("");
    }

    public static int stringToVisibility(String value) {
        return isNullOEmpty(value) ? View.GONE : View.VISIBLE;
    }

    public static <T> boolean isRenderChild(ScreenState<T> state, Class<T> child) {
        return state instanceof ScreenState.Render && child.isAssignableFrom(((ScreenState.Render<T>) state).getRenderState().getClass());
    }
}
