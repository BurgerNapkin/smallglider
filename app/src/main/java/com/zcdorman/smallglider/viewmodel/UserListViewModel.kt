package com.zcdorman.smallglider.viewmodel

import androidx.lifecycle.viewModelScope
import com.zcdorman.smallglider.network.UserCalls
import kotlinx.coroutines.launch

/**
 * ユーザー周りのViewModel
 */
class UserListViewModel : BaseViewModel() {

    /**
     * ユーザーリストを取得する
     */
    fun getUsers() {
        //Todo: param
        viewModelScope.launch {
            UserCalls.getUsers(
                onSuccess = {
                    //Todo: impl
                }
            )
        }
    }

    /**
     * 指摘したユーザー情報を取得する
     */
    fun getUser(userName: String) {
        viewModelScope.launch {
            UserCalls.getUser(
                userName = userName,
                onSuccess = {
                    //Todo: impl
                }
            )
        }
    }
}