package com.zcdorman.smallglider.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel : ViewModel() {
    // ロード中フラグ
    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading
    private val isLoadingAtomic = AtomicBoolean(false)

    /**
     * ロードフラグ切り替える
     */
    fun setIsLoading(
        isLoading: Boolean,
        extra: () -> Unit = {}
    ) {
        _isLoading.value = isLoading
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
        _isLoading.value = true
        extra.invoke()
    }

    /**
     * ロード中出なければFalseにする
     * ロード中だったら、onBusyを呼ぶ
     */
    fun trySetNotLoading(
        onBusy: () -> Unit
    ) {
        if (isLoadingAtomic.get()) {
            onBusy.invoke()
            return
        }
        isLoadingAtomic.set(false)
        _isLoading.value = false
    }
}