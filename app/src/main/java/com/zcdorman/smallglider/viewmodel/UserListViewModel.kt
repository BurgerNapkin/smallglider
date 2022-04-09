package com.zcdorman.smallglider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.UserCalls
import kotlinx.coroutines.launch

/**
 * ユーザー周りのViewModel
 */
class UserListViewModel : BaseViewModel() {
    private val _userList = MutableLiveData<List<User>>().apply { value = listOf() }
    val userList: LiveData<List<User>> = _userList
    private var sinceUserList: Int? = null

    init {
        getUsers()
    }

    /**
     * ユーザーリストを取得する
     */
    fun getUsers() {
        doIfNotLoading {
            viewModelScope.launch {
                UserCalls.getUsers(
                    since = sinceUserList,
                    onSuccess = { userList ->
                        sinceUserList = userList.lastOrNull()?.id
                        val newList = mutableListOf<User>().apply {
                            _userList.value?.also {
                                addAll(it)
                            }
                            addAll(userList)
                        }
                        _userList.postValue(newList)
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