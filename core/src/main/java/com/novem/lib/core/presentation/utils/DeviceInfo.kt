package com.novem.lib.core.presentation.utils


data class DeviceInfo(
    val osVersion: String,
    val apiLevel: String,
    val device: String,
    val model: String,
    val product: String,
    val brand: String,
    val display: String,
    val hardware: String,
    val buildId: String,
    val manufacturer: String,
    val serial: String,
    val user: String,
    val host: String
)