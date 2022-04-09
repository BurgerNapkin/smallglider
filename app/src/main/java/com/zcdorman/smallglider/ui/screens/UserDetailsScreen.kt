package com.zcdorman.smallglider.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * ユーザーの詳細画面
 */
@Composable
fun UserDetailsScreen(
    navController: NavHostController
) {
    ContentView(navController)
}

@Composable
private fun ContentView(
    navController: NavHostController
) {
    Column() {}
}