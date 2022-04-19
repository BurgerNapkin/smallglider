package com.zcdorman.smallglider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.network.UserCalls
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * 詳細ユーザーViewModel
 */
class UserDetailedViewModel : BaseViewModel() {
    private val _user = MutableLiveData<DetailedUser>()
    val user: LiveData<DetailedUser> = _user

    /**
     * 指摘したユーザー情報を取得する
     */
    fun getUser(userName: String) {
        doIfNotLoading {
            viewModelScope.launch {
                UserCalls.getUser(
                    route = NetworkRoutes.UserDetailRoute(userName = userName),
                    onSuccess = {
                        _user.value = it
                    },
                    onError = {
                        //Todo: impl
                    }
                )
                setIsLoading(false)
            }
        }
    }
}