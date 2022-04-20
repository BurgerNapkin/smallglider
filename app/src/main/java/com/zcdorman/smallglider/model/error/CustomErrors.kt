package com.zcdorman.smallglider.model.error

import io.ktor.http.*

sealed class CustomNetworkError(val httpStatusCode: HttpStatusCode) : Exception() {
    class RedirectError(httpStatusCode: HttpStatusCode) : CustomNetworkError(httpStatusCode)
    class ClientRequestError(httpStatusCode: HttpStatusCode) : CustomNetworkError(httpStatusCode)
    class ServerResponseError(httpStatusCode: HttpStatusCode) : CustomNetworkError(httpStatusCode)
    object OtherError : CustomNetworkError(HttpStatusCode(-1, "Other Error"))
}