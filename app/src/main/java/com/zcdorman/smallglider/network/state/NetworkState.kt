package com.zcdorman.smallglider.network.state

import com.zcdorman.smallglider.model.error.CustomNetworkError
import com.zcdorman.smallglider.network.response.base.BaseResponse

sealed class NetworkState {
    object Loading : NetworkState()
    data class Error(val customNetworkError: CustomNetworkError) : NetworkState()
    data class Success<T : BaseResponse>(val response: T) : NetworkState()

    fun isLoading(): Boolean = this is Loading
    fun isError(): Boolean = this is Error
    fun isNotSuccess(): Boolean = this.isLoading() || this.isError()
}

