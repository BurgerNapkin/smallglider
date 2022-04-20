package com.zcdorman.smallglider.extension

import com.zcdorman.smallglider.model.error.CustomNetworkError

fun Exception.toCustomNetworkError(): CustomNetworkError = when (this) {
    is CustomNetworkError -> this
    else -> CustomNetworkError.OtherError
}