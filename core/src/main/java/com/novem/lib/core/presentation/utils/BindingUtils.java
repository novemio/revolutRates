package com.novem.lib.core.presentation.utils;

import android.view.View;

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
}
