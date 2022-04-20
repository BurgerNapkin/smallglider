package com.zcdorman.smallglider.extension

import com.zcdorman.smallglider.model.error.CustomNetworkError
import io.ktor.http.*

fun HttpStatusCode.toCustomNetworkError(): CustomNetworkError {
    return when (value) {
        in 300..399 -> CustomNetworkError.RedirectError(this)
        in 400..499 -> CustomNetworkError.ClientRequestError(this)
        in 500..599 -> CustomNetworkError.ServerResponseError(this)
        else -> CustomNetworkError.OtherError
    }
}