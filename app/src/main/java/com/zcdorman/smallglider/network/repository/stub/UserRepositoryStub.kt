package com.zcdorman.smallglider.network.repository.stub

import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.repository.base.BaseRepository
import com.zcdorman.smallglider.network.response.GetUserListResponse
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import com.zcdorman.smallglider.network.state.NetworkState
import com.zcdorman.smallglider.viewmodel.base.BaseViewModel
import kotlinx.coroutines.delay

class UserRepositoryStub(private val baseViewModel: BaseViewModel) : BaseRepository(baseViewModel) {
    private val delay = 5000L
    private val polling = 50

    suspend fun getUserListStub(
        route: NetworkRoutes.SearchUsersRoute,
        onSuccess: (response: GetUserListResponse) -> Unit
    ) {
        updateNetworkState(NetworkState.Loading)
        val start = route.since?.plus(1) ?: 0
        val end = start + (polling - 1)
        val gitUserList = (start..end).map {
            User(
                "User Name $it",
                it,
                "$it",
                "urlAvatar",
                "gravatarId",
                "url",
                "urlHtml",
                "urlFollowers",
                "urlFollowing",
                "urlGists",
                "urlStarred",
                "urlSubscriptions",
                "urlOrganizations",
                "urlRepos",
                "urlEvents",
                "urlReceivedEvents",
                "type",
                false
            )
        }
        delay(delay)
        val response = GetUserListResponse(gitUserList)
        onSuccess.invoke(response)
        updateNetworkState(NetworkState.Success(response))
    }
}