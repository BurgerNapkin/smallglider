package com.zcdorman.smallglider.network

import android.util.Log
import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.client.Clients
import com.zcdorman.smallglider.network.routes.NetworkRoutes
import io.ktor.client.request.*

/**
 * ユーザー周りのコール
 *
 * todo エラー対応
 */
object UserCalls {

    /**
     * ユーザーの一覧取得
     *
     * @param route
     * @param onSuccess
     * @param onError
     */
    suspend fun getUserList(
        route: NetworkRoutes.SearchUsersRoute,
        onSuccess: (users: List<User>) -> Unit,
        onError: () -> Unit
    ) {
        try {
            val users = Clients.defaultClient.get<List<User>>(route.url) {
                route.since?.also { since ->
                    parameter("since", since)
                }
                parameter("per_page", route.perPage)
            }
            onSuccess.invoke(users)
        } catch (e: Exception) {
            Log.e("API", e.toString())
            onError.invoke()
        }
    }

    /**
     * ユーザー詳細取得
     *
     * @param route
     * @param onSuccess
     * @param onError
     */
    suspend fun getUser(
        route: NetworkRoutes.UserDetailRoute,
        onSuccess: (detailedUser: DetailedUser) -> Unit,
        onError: () -> Unit,
    ) {
        try {
            val user = Clients.defaultClient.get<DetailedUser>(route.url)
            onSuccess.invoke(user)
        } catch (e: Exception) {
            Log.e("API", e.toString())
            onError.invoke()
        }
    }
}