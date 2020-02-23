package com.novem.lib.core.domain

sealed class NetworkException(message: String) : Exception(message)

class NoResponseBodyException(override val message: String) : NetworkException(message)

class UnAuthorizedUserException(override val message: String) : NetworkException(message)

class SessionActiveOnOtherDeviceException(override val message: String) : NetworkException(message)

class UnknownNetworkException(override val message: String) : NetworkException(message)

class NoNetworkConnectionException(override val message: String): NetworkException(message)
