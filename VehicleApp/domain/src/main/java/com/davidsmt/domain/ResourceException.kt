package com.davidsmt.domain

/**
 * Created by David SMT on 18/01/2019.
 */
sealed class ResourceException(message: String, exception: Exception? = null) : Exception(message, exception) {
    class NullOrEmptyResource(message: String) : ResourceException(message)
    class ApiError(message: String) : ResourceException(message)
    class RemoteResponseError(message: String, exception: Exception? = null) : ResourceException(message, exception)
}
