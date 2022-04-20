package com.zcdorman.smallglider.viewmodel.base

import androidx.lifecycle.ViewModel
import com.zcdorman.smallglider.network.state.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
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
    val networkState = _networkState

    /**
     * 通信状態を更新する
     *
     * @param networkState
     */
    fun updateNetworkState(networkState: NetworkState) {
        _networkState.tryEmit(networkState)
        setIsLoading(false)
    }

    /**
     * ロードフラグ切り替える
     */
    private fun setIsLoading(
        isLoading: Boolean,
        extra: () -> Unit = {}
    ) {
        isLoadingAtomic.set(isLoading)
        extra.invoke()
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