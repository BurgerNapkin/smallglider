package com.zcdorman.smallglider.network.routes

sealed class NetworkRoutes(suffix: String) {
    private val baseUrl = "https://api.github.com/"
    val url: String = baseUrl + suffix

    data class SearchUsersRoute(val since: Int? = null, val perPage: Int = 50) : NetworkRoutes("users")
    data class UserDetailRoute(val userName: String) : NetworkRoutes(userName)
}
