package com.novem.lib.core.presentation.utils

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by novemio on 2/16/20.
 */

data class ScreenSize(val width: Int, val height: Int, val inch: Double){

   fun  toResolution()="${width}x${height}"
}


object DeviceUtils {

    private val TAG by lazy { DeviceUtils::class.java.simpleName }


    fun getScreenDimension(context: Context): ScreenSize {
        val dm = DisplayMetrics()

        val ws = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        ws.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val dens = dm.densityDpi;
        val wi: Double = (width / dens).toDouble()
        val hi = (height / dens).toDouble()
        val x = wi.pow(2)
        val y = hi.pow(2)
        val screenInches = sqrt(x + y)

        return ScreenSize(width, height, screenInches)
    }
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    fun getDeviceInfo() = DeviceInfo(
        System.getProperty("os.version") ?: "Unknown", // OS version
        Build.VERSION.SDK,     // API Level
        Build.DEVICE,          // Device
        Build.MODEL,            // Model
        Build.PRODUCT,
        Build.BRAND,
        Build.DISPLAY,
        Build.HARDWARE,
        Build.ID,
        Build.MANUFACTURER,
        Build.getSerial(),
        Build.USER,
        Build.HOST
    )        // Product


     fun getDeviceSuperInfo() {

        Log.i(TAG, "getDeviceSuperInfo")
        try {
            var s = "Debug-infos:"
            s += "\n OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")"
            s += "\n OS API Level: " + Build.VERSION.SDK_INT
            s += "\n Device: " + Build.DEVICE
            s += "\n Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")"
            s += "\n RELEASE: " + Build.VERSION.RELEASE
            s += "\n BRAND: " + Build.BRAND
            s += "\n DISPLAY: " + Build.DISPLAY
            s += "\n CPU_ABI: " + Build.CPU_ABI
            s += "\n CPU_ABI2: " + Build.CPU_ABI2
            s += "\n UNKNOWN: " + Build.UNKNOWN
            s += "\n HARDWARE: " + Build.HARDWARE
            s += "\n Build ID: " + Build.ID
            s += "\n MANUFACTURER: " + Build.MANUFACTURER
            s += "\n SERIAL: " + Build.SERIAL
            s += "\n USER: " + Build.USER
            s += "\n HOST: " + Build.HOST
            Log.i(TAG.toString() + " | Device Info > ", s)
        } catch (e: Exception) {
            Log.e(TAG, "Error getting Device INFO")
        }
    }
}