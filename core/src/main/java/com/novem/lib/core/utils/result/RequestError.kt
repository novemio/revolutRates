package com.novem.lib.core.utils.result

sealed class RequestError(val message: String?) {

	data class UnrecognizedError(val t: Throwable) : RequestError(t.message)
	data class NoInternetError(val t: Throwable? = null) : RequestError("No Internet")
	class HttpError(val code: Int, message: String?) : RequestError(message)
	class InternalServerError(message: String?) : RequestError(message)
	class UserNotAuthorised(message: String? = null) : RequestError(message)
}