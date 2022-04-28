package com.zcdorman.smallglider.network.routes

sealed class NetworkRoutes(suffix: String) {
    private val baseUrl = "https://api.github.com/"
    val url: String = baseUrl + suffix

    data class SearchUsersRoute(val since: Int? = null, val perPage: Int = 50) :
        NetworkRoutes("users")

    data class UserDetailRoute(val userName: String) : NetworkRoutes("users/$userName")
    
    data class UserFollowing(val userName: String, val page: Int, val perPage: Int) :
        NetworkRoutes("users/$userName/following")

    data class UserFollowers(val userName: String, val page: Int, val perPage: Int) :
        NetworkRoutes("users/$userName/followers")

    data class UserRepos(val userName: String, val page: Int, val perPage: Int) :
        NetworkRoutes("users/$userName/repos")
}
