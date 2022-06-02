package com.zcdorman.smallglider.ui.composeable.screens.userDetailsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.model.data.Repo
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.ui.composeable.common.ErrorViewGeneral
import com.zcdorman.smallglider.ui.composeable.common.LoadingViewGeneral
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

@OptIn(ExperimentalPagerApi::class)
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
        viewModel.getDetailedUser(userName)
    }

    // ロード中ビュー
    val networkState = viewModel.networkState.collectAsState()
    if (networkState.value.isLoading()) {
        LoadingViewGeneral()
    }
    if (networkState.value.isError()) {
        ErrorViewGeneral()
    }

    val userState = viewModel.user.collectAsState()
    val user = userState.value ?: return
    val followers = viewModel.followers.collectAsState()
    val following = viewModel.following.collectAsState()
    val repos = viewModel.repos.collectAsState()

    UserContent(
        user = user,
        followers = followers.value,
        following = following.value,
        repos = repos.value,
        onBackPressed = {
            navController.popBackStack()
        },
        onEndOfFollowersListReached = { currentItemCount ->
            //Todo:
        },
        onEndOfFollowingListReached = { currentItemCount ->
            //Todo:
        },
        onEndOfRepoListReached = { currentItemCount ->
            //Todo:
        }
    )
}

/**
 * ユーザー情報
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun UserContent(
    user: DetailedUser,
    followers: List<User>,
    following: List<User>,
    repos: List<Repo>,
    onBackPressed: () -> Unit,
    onEndOfFollowersListReached: (currentItemCount: Int) -> Unit,
    onEndOfFollowingListReached: (currentItemCount: Int) -> Unit,
    onEndOfRepoListReached: (currentItemCount: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            UserDetailDetailsComponent(
                user,
                onBackPressed
            )
        }

        val pagerState = rememberPagerState()
        HorizontalPager(
            count = PageEntries.values().size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { pageIndex ->
            // Our page content
            when (pageIndex) {
                PageEntries.FOLLOWERS.ordinal -> UsersDetailsUserListComponent(
                    title = stringResource(id = R.string.followers),
                    users = followers,
                    onEndOfFollowersListReached
                )
                PageEntries.FOLLOWING.ordinal -> UsersDetailsUserListComponent(
                    title = stringResource(id = R.string.following),
                    users = following,
                    onEndOfFollowingListReached
                )
                PageEntries.REPOS.ordinal -> UserDetailsRepoListComponent(
                    repos = repos,
                    onEndOfRepoListReached
                )
            }
        }
    }
}

private enum class PageEntries {
    FOLLOWERS,
    FOLLOWING,
    REPOS
}
