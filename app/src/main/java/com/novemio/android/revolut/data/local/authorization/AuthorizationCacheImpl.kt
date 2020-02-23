package com.novemio.android.revolut.data.local.authorization

import android.content.SharedPreferences
import androidx.core.content.edit
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by novemio on 7/5/19.
 */

internal const val CACHE_AUTH_TOKEN_KEY = "auth_tok3n_k3y"

@Singleton
class AuthorizationCacheImpl @Inject constructor(
	private val sharedPreferences: SharedPreferences
) : AuthorizationCache {
	
	private val moshi = Moshi.Builder()
		.build()
	
	override fun getAuthToken() =
		sharedPreferences.getString(CACHE_AUTH_TOKEN_KEY, null)?.let {
			return@let AuthTokenJsonAdapter(moshi).fromJson(it)
		}
	
	override fun saveAuthToken(authToken: AuthToken) =
		sharedPreferences.edit {
			putString(CACHE_AUTH_TOKEN_KEY, AuthTokenJsonAdapter(moshi).toJson(authToken))
		}
	
	override fun clear() {
		sharedPreferences.edit {
			clear()
		}
	}
}

