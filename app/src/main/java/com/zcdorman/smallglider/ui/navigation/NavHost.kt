package com.zcdorman.smallglider.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zcdorman.smallglider.ui.composeable.screens.userListScreen.UserListScreen
import com.zcdorman.smallglider.ui.composeable.screens.userDetailsScreen.UserDetailsScreen

@Composable
fun SmallGliderNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.UserList.routeName
    ) {
        composable(NavigationRoutes.UserList.routeName) {
            UserListScreen(
                navController
            )
        }
        composable(
            NavigationRoutes.UserDetails.ROUTE_NAME,
            arguments = listOf(
                navArgument(NavigationRoutes.UserDetails.NAV_ARG_NAME) { type = NavType.StringType }
            )
        ) {
            UserDetailsScreen(
                navController,
                it.arguments?.getString(NavigationRoutes.UserDetails.NAV_ARG_NAME)
            )
        }
    }
}