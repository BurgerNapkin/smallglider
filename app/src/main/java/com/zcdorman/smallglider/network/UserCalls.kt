package com.zcdorman.smallglider.network

import com.zcdorman.smallglider.model.data.User
import io.ktor.client.request.*

/**
 * ユーザー周りのコール
 *
 * todo エラー対応
 */
object UserCalls {

    /**
     * ユーザーリスト取得
     * @param since 指摘したユーザーIDからデータを
     * @param perPage ページング数
     */
    suspend fun getUsers(
        since: Int? = null,
        perPage: Int = 30,
        onSuccess: (users: List<User>) -> Unit,
        onError: () -> Unit
    ) {
        val url = "https://api.github.com/users"
        try {
            val users = Clients.defaultClient.get<List<User>>(url) {
                since?.also {
                    parameter("since", it)
                }
                parameter("per_page", perPage)
            }
            onSuccess.invoke(users)
        } catch (e: Exception) {
            onError.invoke()
        }
    }

    /**
     * 指摘したユーザーの情報を取得
     * @param userName
     */
    suspend fun getUser(
        userName: String,
        onSuccess: (users: User) -> Unit,
        onError: () -> Unit,
    ) {
        val url = "https://api.github.com/users/$userName"
        try {
            val user = Clients.defaultClient.get<User>(url)
            onSuccess.invoke(user)
        } catch (e: Exception) {
            onError.invoke()
        }
    }
}