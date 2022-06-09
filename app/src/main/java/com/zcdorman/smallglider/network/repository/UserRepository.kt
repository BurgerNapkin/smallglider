package com.zcdorman.smallglider.network.repository

import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.model.data.PagingHelper
import com.zcdorman.smallglider.model.data.Repo
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.client.Clients
import com.zcdorman.smallglider.network.repository.base.BaseRepository
import com.zcdorman.smallglider.network.request.GetUserFollowersRequest
import com.zcdorman.smallglider.network.request.GetUserFollowingRequest
import com.zcdorman.smallglider.network.request.GetUserReposRequest
import com.zcdorman.smallglider.network.response.*
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.network.state.NetworkState
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * ユーザー検索のリポ
 *
 * @constructor
 * @param baseViewModel
 */
class UserRepository(baseViewModel: BaseViewModel) : BaseRepository(baseViewModel) {

    /**
     * ユーザー一覧取得
     *
     * @param route
     * @param onSuccess
     */
    suspend fun getUserList(
        route: NetworkRoutes.SearchUsersRoute,
        onSuccess: (response: GetUserListResponse) -> Unit
    ) {
        updateNetworkState(NetworkState.Loading)
        try {
            val users: List<User> = Clients.defaultClient.get(route.url) {
                route.since?.also { since ->
                    parameter("since", since)
                }
                parameter("per_page", route.perPage)
            }.body()
            val response = GetUserListResponse(users)
            onSuccess.invoke(response)
            updateNetworkState(NetworkState.Success(response))
        } catch (e: Exception) {
            updateNetworkState(e)
        }
    }

    /**
     * ユーザー詳細取得
     *
     * @param route
     * @param onSuccess
     */
    suspend fun getUser(
        route: NetworkRoutes.UserDetailRoute,
        onSuccess: (detailedUser: DetailedUser) -> Unit
    ) {
        try {
            val user: DetailedUser = Clients.defaultClient.get(route.url).body()
            onSuccess.invoke(user)
            val getUserDetailedResponse = GetUserDetailResponse(user)
            updateNetworkState(NetworkState.Success(getUserDetailedResponse))
        } catch (e: Exception) {
            updateNetworkState(e)
        }
    }

    suspend fun getUserFollowers(
        request: GetUserFollowersRequest,
        onSuccess: (response: GetUserFollowersResponse) -> Unit
    ) {
        try {
            val httpResponse = Clients.defaultClient.get(
                NetworkRoutes.UserFollowers(
                    userName = request.userName,
                    page = request.pagingHelper.page,
                    perPage = request.pagingHelper.perPage
                ).url
            )
            val userList: List<User> = httpResponse.body()
            val hasNext = userList.size == request.pagingHelper.perPage
            val response = GetUserFollowersResponse(
                userName = request.userName,
                nextPagingHelper = PagingHelper(
                    page = request.pagingHelper.page + 1,
                    hasNext = hasNext,
                    perPage = request.pagingHelper.perPage
                ),
                users = userList
            )
            onSuccess.invoke(response)
            updateNetworkState(NetworkState.Success(response))
        } catch (e: Exception) {
            updateNetworkState(e)
        }
    }

    suspend fun getUserFollowing(
        request: GetUserFollowingRequest,
        onSuccess: (response: GetUserFollowingResponse) -> Unit
    ) {
        try {
            val httpResponse = Clients.defaultClient.get(
                NetworkRoutes.UserFollowing(
                    userName = request.userName,
                    page = request.pagingHelper.page,
                    perPage = request.pagingHelper.perPage
                ).url
            )
            val userList: List<User> = httpResponse.body()
            val hasNext = userList.size == request.pagingHelper.perPage
            val response = GetUserFollowingResponse(
                userName = request.userName,
                nextPagingHelper = PagingHelper(
                    page = request.pagingHelper.page + 1,
                    hasNext = hasNext,
                    perPage = request.pagingHelper.perPage
                ),
                users = userList
            )
            onSuccess.invoke(response)
            updateNetworkState(NetworkState.Success(response))
        } catch (e: Exception) {
            updateNetworkState(e)
        }
    }

    suspend fun getUserRepos(
        request: GetUserReposRequest,
        onSuccess: (response: GetUserRepoResponse) -> Unit
    ) {
        try {
            val httpResponse = Clients.defaultClient.get(
                NetworkRoutes.UserRepos(
                    userName = request.userName,
                    page = request.pagingHelper.page,
                    perPage = request.pagingHelper.perPage
                ).url
            )
            val repos: List<Repo> = httpResponse.body()
            val response = GetUserRepoResponse(
                userName = request.userName,
                nextPagingHelper = PagingHelper(
                    request.pagingHelper.page + 1,

                    ),
                repos = repos
            )
            onSuccess.invoke(response)
            updateNetworkState(NetworkState.Success(response))
        } catch (e: Exception) {
            updateNetworkState(e)
        }
    }
}