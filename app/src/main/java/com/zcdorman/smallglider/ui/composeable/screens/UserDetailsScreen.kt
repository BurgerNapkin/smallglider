package com.zcdorman.smallglider.ui.composeable.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.ui.composeable.common.LoadingView
import com.zcdorman.smallglider.viewmodel.UserDetailedViewModel

/**
 * ユーザーの詳細画面
 */
@Composable
fun UserDetailsScreen(
    navController: NavHostController,
    userName: String?
) {
    ContentView(
        navController,
        userName
    )
}

@Composable
private fun ContentView(
    navController: NavHostController,
    userName: String?,
    viewModel: UserDetailedViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    if (userName == null) {
        //Todo: エラーハンドリング, then go back
        navController.popBackStack()
        return
    }

    // 一回のみ実行する
    val onlyOnLaunch by remember { mutableStateOf(0) }
    LaunchedEffect(onlyOnLaunch) {
        viewModel.getUser(userName)
    }

    // ロード中ビュー
    val isLoading = viewModel.isLoading.observeAsState()
    if (isLoading.value == true) {
        LoadingView()
    }

    val userState = viewModel.user.observeAsState()
    val user = userState.value ?: return //Todo: エラーハンドリング

    UserContent(
        user = user,
        onBackPressed = {
            navController.popBackStack()
        }
    )
}

/**
 * ユーザー情報
 */
@Composable
private fun UserContent(
    user: DetailedUser?,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row {
            Image(
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .clickable {
                        onBackPressed.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_back_arrow_black),
                contentDescription = stringResource(id = R.string.content_description_navigation_back)
            )
        }
        if (user == null) {
            return
        }
        SpacerVertical()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.urlAvatar)
                .crossfade(false)
                .build(),
            error = painterResource(id = R.drawable.github_mark_dark),
            contentDescription = stringResource(id = R.string.content_description_user_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp)
                .clip(CircleShape)
                .aspectRatio(1f)
                .fillMaxWidth()
        )
        SpacerVertical()
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = user.nameLogin,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        SpacerVertical()
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = user.nameFull,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SpacerVertical() {
    Spacer(modifier = Modifier.height(16.dp))
}