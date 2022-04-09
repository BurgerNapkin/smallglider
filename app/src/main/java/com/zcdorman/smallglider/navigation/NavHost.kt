package com.zcdorman.smallglider.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zcdorman.smallglider.ui.screens.UserDetailsScreen
import com.zcdorman.smallglider.ui.screens.UserListScreen

@Composable
fun SmallGliderNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.UserList.routeName
    ) {
        composable(Routes.UserList.routeName) {
            UserListScreen(
                navController
            )
        }
        composable(
            Routes.UserDetails.ROUTE_NAME,
            arguments = listOf(
                navArgument(Routes.UserDetails.NAV_ARG_NAME) { type = NavType.StringType }
            )
        ) {
            UserDetailsScreen(
                navController,
                it.arguments?.getString(Routes.UserDetails.NAV_ARG_NAME)
            )
        }
    }
}