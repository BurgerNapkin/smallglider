package com.zcdorman.smallglider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.repository.UserRepository
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 * ユーザー周りのViewModel
 */
class UserListViewModel : BaseViewModel() {
    private val userRepo = UserRepository(this)
    private val _userList = MutableLiveData<List<User>>().apply { value = listOf() }
    val userList: LiveData<List<User>> = _userList
    private var sincePolling: Int? = null

    init {
        refreshUserList()
    }

    /**
     * ユーザーリストを取得する
     */
    private fun getUserList() {
        doIfNotLoading {
            viewModelScope.launch {
                userRepo.getUserList(
                    NetworkRoutes.SearchUsersRoute(since = sincePolling),
                    onSuccess = { response ->
                        sincePolling = response.userList.lastOrNull()?.id
                        val newList = mutableListOf<User>().apply {
                            _userList.value?.also {
                                addAll(it)
                            }
                            addAll(response.userList)
                        }
                        _userList.value = newList
                    }
                )
            }
        }
    }

    /**
     * 一覧データをリセットして、最初からデータ取得
     */
    private fun refreshUserList() {
        _userList.postValue(emptyList())
        sincePolling = null
        getUserList()
    }

    /**
     * ユーザーリストの最後の項目が表示された
     */
    fun onEndOfListReached(itemCount: Int) {
        if (networkState.value.isNotSuccess() || (_userList.value?.count() ?: 0) > itemCount) {
            return
        }
        getUserList()
    }
}