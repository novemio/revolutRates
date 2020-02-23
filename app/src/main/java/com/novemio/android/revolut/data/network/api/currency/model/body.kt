package com.novemio.android.revolut.data.network.api.currency.model



data class DeviceDataBody(
    val deviceName: String,
    val description: String)


data class DeviceStatusBody(
    val availableInternalMemory: Int,
    val deviceStatus: Int
)
