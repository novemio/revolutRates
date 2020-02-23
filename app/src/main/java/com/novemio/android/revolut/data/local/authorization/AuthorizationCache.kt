package com.novemio.android.revolut.data.local.authorization

/**
 * Created by novemio on 7/5/19.
 */
interface AuthorizationCache {
	
	fun getAuthToken(): AuthToken?
	fun saveAuthToken(authToken: AuthToken)
	fun clear()
	
}

