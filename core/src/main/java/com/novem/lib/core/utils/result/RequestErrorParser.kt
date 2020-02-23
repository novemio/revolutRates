package com.novem.lib.core.utils.result

import com.novem.lib.core.data.network.SessionExpired
import com.novem.lib.core.utils.result.RequestError.HttpError
import com.novem.lib.core.utils.result.RequestError.InternalServerError
import com.novem.lib.core.utils.result.RequestError.NoInternetError
import com.novem.lib.core.utils.result.RequestError.UnrecognizedError
import com.novem.lib.core.utils.result.RequestError.UserNotAuthorised

import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

object RequestErrorParser {
	fun parse(exception: Throwable): RequestError =
		
		when (exception) {
			is HttpException ->
				if (exception.code() == 500 || exception.code() == 502) InternalServerError(exception.message())
				else HttpError(exception.code(), exception.message())
			is UnknownHostException -> NoInternetError(exception)
			is ConnectException -> NoInternetError(exception)
			is SessionExpired -> UserNotAuthorised("User not authorised")
			else -> UnrecognizedError(exception)
		}
	
}