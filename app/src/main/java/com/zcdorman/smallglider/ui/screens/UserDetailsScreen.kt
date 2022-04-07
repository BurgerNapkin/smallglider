package com.zcdorman.smallglider.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zcdorman.smallglider.model.data.User

@Composable
fun UserDetailsScreen(
    user: User
) {
    ContentView(
        user
    )
}

@Composable
private fun ContentView(
    user: User
) {
    Column() {
        Text(user.name)
    }
}