package com.zcdorman.smallglider.viewmodel

import androidx.lifecycle.viewModelScope
import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.model.data.Repo
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.repository.UserRepository
import com.zcdorman.smallglider.network.response.base.BaseResponse
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.network.state.NetworkState
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 詳細ユーザーViewModel
 */
class UserDetailedViewModel : BaseViewModel() {
    private val userRepo = UserRepository(this)

    // user for detailed info
    private val _user: MutableStateFlow<DetailedUser?> = MutableStateFlow(null)
    val user: StateFlow<DetailedUser?> = _user
    var userName: String = ""

    //followers network state and list
    private var followingNetworkState: NetworkState = NetworkState.Success(BaseResponse())
    private val _following: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val following: StateFlow<List<User>> = _following

    // user's followers
    private var followersNetworkState: NetworkState = NetworkState.Success(BaseResponse())
    private val _followers: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val followers: StateFlow<List<User>> = _followers

    // user's repos
    private var reposNetworkState: NetworkState = NetworkState.Success(BaseResponse())
    private val _repos: MutableStateFlow<List<Repo>> = MutableStateFlow(emptyList())
    val repos: StateFlow<List<Repo>> = _repos

    // values per page
    private val pageCount = 20

    // current page for each list
    private var followersPageCount = 1
    private var followingPageCount = 1
    private var reposPageCount = 1


    /**
     * 指摘したユーザー情報を取得する
     */
    fun getDetailedUser(userName: String) {
        this.userName = userName
        doIfNotLoading {
            viewModelScope.launch {
                getBasicInfo(userName)
                getFollowers()
                getFollowing()
                getRepos()
            }
        }
    }

    /**
     * ユーザー詳細取得
     *
     * @param userName
     */
    private fun getBasicInfo(userName: String) {
        viewModelScope.launch {
            userRepo.getUser(
                route = NetworkRoutes.UserDetailRoute(userName = userName),
                onSuccess = {
                    _user.tryEmit(
                        it
                    )
                }
            )
        }
    }

    /**
     * フォローワーリスト取得
     *
     * @param userName
     */
    fun getFollowers(
        userName: String = this.userName
    ) {
        if (followersNetworkState.isNotSuccess()) {
            return
        }
        followersNetworkState = NetworkState.Loading
        viewModelScope.launch {
            userRepo.getUserFollowers(
                NetworkRoutes.UserFollowers(
                    userName,
                    followersPageCount,
                    pageCount
                )
            ) {
                followersPageCount = it.page
                _followers.apply {
                    tryEmit(
                        value.toMutableList().apply {
                            addAll(it.users)
                        }
                    )
                }
                followersNetworkState = NetworkState.Success(it)
            }
        }
    }

    /**
     * フォロー中ユーザーリスト取得
     *
     * @param userName
     */
    fun getFollowing(
        userName: String = this.userName
    ) {
        if (followingNetworkState.isNotSuccess()) {
            return
        }
        followingNetworkState = NetworkState.Loading
        viewModelScope.launch {
            userRepo.getUserFollowing(
                NetworkRoutes.UserFollowing(
                    userName,
                    followingPageCount,
                    pageCount
                )
            ) {
                followingPageCount = it.page
                _following.apply {
                    tryEmit(
                        value.toMutableList().apply {
                            addAll(it.users)
                        }
                    )
                }
                followingNetworkState = NetworkState.Success(it)
            }
        }
    }

    /**
     * ユーザーのRepoリスト取得
     *
     * @param userName
     */
    fun getRepos(
        userName: String = this.userName
    ) {
        if (reposNetworkState.isNotSuccess()) {
            return
        }
        reposNetworkState = NetworkState.Loading
        viewModelScope.launch {
            userRepo.getUserRepos(
                NetworkRoutes.UserRepos(
                    userName,
                    reposPageCount,
                    pageCount
                )
            ) {
                reposPageCount = it.page
                _repos.apply {
                    tryEmit(
                        value.toMutableList().apply {
                            addAll(it.repos)
                        }
                    )
                }
                reposNetworkState = NetworkState.Success(it)
            }
        }
    }
}