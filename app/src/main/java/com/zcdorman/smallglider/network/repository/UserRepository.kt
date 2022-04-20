package com.zcdorman.smallglider.network.repository

import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.client.Clients
import com.zcdorman.smallglider.network.repository.base.BaseRepository
import com.zcdorman.smallglider.network.response.GetUserDetailResponse
import com.zcdorman.smallglider.network.response.GetUserListResponse
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.network.state.NetworkState
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import io.ktor.client.call.*
import io.ktor.client.request.*

/**
 * ユーザー検索のリポ
 * TODO: Injection
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
        try {
            val users: List<User> = Clients.defaultClient.get(route.url) {
                route.since?.also { since ->
                    parameter("since", since)
                }
                parameter("per_page", route.perPage)
            }.body()
            onSuccess.invoke(GetUserListResponse(users))
            val getUserListResponse = GetUserListResponse(users)
            updateNetworkState(NetworkState.Success(getUserListResponse))
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
}