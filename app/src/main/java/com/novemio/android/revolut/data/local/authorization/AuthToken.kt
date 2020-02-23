package com.novemio.android.revolut.data.local.authorization

import com.squareup.moshi.JsonClass

/**
 * Created by novemio on 7/7/19.
 */

@JsonClass(generateAdapter = true)
data class AuthToken(val userId:String,val token:String)