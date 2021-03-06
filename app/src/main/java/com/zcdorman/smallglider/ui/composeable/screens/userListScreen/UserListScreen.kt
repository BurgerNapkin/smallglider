package com.zcdorman.smallglider.ui.composeable.screens.userListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.extension.isLastVisible
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.ui.composeable.common.ErrorViewGeneral
import com.zcdorman.smallglider.ui.composeable.common.LoadingViewGeneral
import com.zcdorman.smallglider.ui.navigation.NavigationRoutes
import com.zcdorman.smallglider.viewmodel.UserListViewModel

/**
 * ユーザーの一覧リスト
 */
@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    ContentView(
        navController,
        viewModel
    )
}

@Composable
private fun ContentView(
    navController: NavHostController,
    viewModel: UserListViewModel
) {
    val networkState = viewModel.networkState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (networkState.value.isError()) {
            ErrorViewGeneral()
        } else {
            UserListView(
                viewModel,
                navController
            )
        }
        if (networkState.value.isLoading()) {
            LoadingViewGeneral()
        }
    }
}

@Composable
private fun UserListView(
    viewModel: UserListViewModel,
    navController: NavHostController
) {
    val listState = rememberLazyListState()
    val users = viewModel.userList.observeAsState()
    LazyColumn(
        state = listState
    ) {
        items(
            items = users.value ?: emptyList(),
            key = { user ->
                user.id
            }
        ) { user ->
            UserView(user) {
                user.userName.also {
                    navController.navigate(NavigationRoutes.UserDetails(it).fullRoute)
                }
            }
        }
        if (listState.isLastVisible()) {
            val totalItemCount = listState.layoutInfo.totalItemsCount
            viewModel.onEndOfListReached(totalItemCount)
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
                .data(user.urlAvatar)
                .crossfade(false)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_github_mark),
            error = painterResource(id = R.drawable.ic_github_mark),
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
            Text(text = user.userName ?: "")
            Text(text = "User ID: ${user.id}")
        }
        RowSpacer()
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = stringResource(id = R.string.content_description_navigation_next)
        )
        RowSpacer()
    }
}

@Composable
private fun RowSpacer() {
    Spacer(modifier = Modifier.width(16.dp))
}