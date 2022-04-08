package com.zcdorman.smallglider.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.model.data.User

@Composable
fun UserListScreen() {
    ContentView()
}

@Composable
private fun ContentView() {
    //todo UserListView
}

@Composable
private fun UserListView(users: List<User>) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState
    ) {
        items(
            items = users
        ) { user ->
            UserView(user) {
                //todo row click
            }
        }
    }
}

@Composable
private fun UserView(
    user: User,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RowSpacer()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.imageUrl)
                .crossfade(false)
                .build(),
            placeholder = painterResource(id = R.drawable.github_mark_dark),
            error = painterResource(id = R.drawable.github_mark_dark),
            contentDescription = stringResource(id = R.string.content_description_user_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )
        RowSpacer()
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = user.name)
            Text(text = user.description)
        }
        RowSpacer()
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_navigate_next_black),
            contentDescription = stringResource(id = R.string.content_description_navigation_next)
        )
        RowSpacer()
    }
}

@Composable
private fun RowSpacer() {
    Spacer(modifier = Modifier.width(16.dp))
}