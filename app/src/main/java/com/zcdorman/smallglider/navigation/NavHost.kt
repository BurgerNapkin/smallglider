package com.zcdorman.smallglider.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zcdorman.smallglider.ui.screens.UserDetailsScreen
import com.zcdorman.smallglider.ui.screens.UserListScreen

@Composable
fun SmallGliderNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.USER_LIST.name
    ) {
        composable(Routes.USER_LIST.name) {
            UserListScreen(navController)
        }
        composable(Routes.USER_DETAILS.name) {
            UserDetailsScreen(navController)
        }
    }
}