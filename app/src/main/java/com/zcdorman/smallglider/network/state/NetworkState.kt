package com.zcdorman.smallglider.network.state

import com.zcdorman.smallglider.network.response.base.BaseResponse

sealed class NetworkState {
    object Loading : NetworkState()
    data class Error(val errorText: String) : NetworkState()
    data class Response(val response: BaseResponse) : NetworkState()
}

