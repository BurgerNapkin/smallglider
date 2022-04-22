package com.zcdorman.smallglider.viewmodel.base

import androidx.lifecycle.ViewModel
import com.zcdorman.smallglider.network.state.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.atomic.AtomicBoolean

/**
 * BaseViewModel
 *
 */
open class BaseViewModel : ViewModel() {
    /**
     * ロードフラグ
     */
    private val isLoadingAtomic = AtomicBoolean(false)

    /**
     * 通信状態
     */
    private val _networkState = MutableStateFlow<NetworkState>(NetworkState.Loading)
    val networkState: StateFlow<NetworkState> = _networkState

    /**
     * 通信状態を更新する
     *
     * @param networkState
     */
    fun updateNetworkState(networkState: NetworkState) {
        _networkState.tryEmit(networkState)
        setIsLoading(networkState.isLoading())
    }

    /**
     * ロードフラグ切り替える
     */
    private fun setIsLoading(
        isLoading: Boolean
    ) {
        isLoadingAtomic.set(isLoading)
    }

    /**
     * ロード中じゃない場合、extraを呼ぶ
     */
    fun doIfNotLoading(
        extra: () -> Unit
    ) {
        if (isLoadingAtomic.get()) {
            return
        }
        isLoadingAtomic.set(true)
        updateNetworkState(NetworkState.Loading)
        extra.invoke()
    }
}