package com.zcdorman.smallglider.ui.navigation

sealed class NavigationRoutes(val routeName: String) {
    object UserList : NavigationRoutes("user_list")
    class UserDetails(name: String) : NavigationRoutes("${NAV_PRE_FIX}{${NAV_ARG_NAME}}") {
        val fullRoute = "${NAV_PRE_FIX}$name"

        companion object {
            const val NAV_ARG_NAME = "userName"
            const val NAV_PRE_FIX = "user_details/"
            const val ROUTE_NAME = "${NAV_PRE_FIX}{${NAV_ARG_NAME}}"
        }
    }
}