package com.zcdorman.smallglider.network.repository.base

import com.zcdorman.smallglider.extension.toCustomNetworkError
import com.zcdorman.smallglider.network.state.NetworkState
import com.zcdorman.smallglider.network.state.NetworkState.Error
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import java.lang.Exception

open class BaseRepository(private val viewModel: BaseViewModel) {
    fun updateNetworkState(networkState: NetworkState) = viewModel.updateNetworkState(networkState)
    fun updateNetworkState(exception: Exception) = updateNetworkState(Error(exception.toCustomNetworkError()))
}