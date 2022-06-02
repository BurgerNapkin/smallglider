package com.zcdorman.smallglider.ui.composeable.screens.userDetailsScreen

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.extension.isLastVisible
import com.zcdorman.smallglider.model.data.User

/**
 * ユーザー詳細のフォローとフォロワーリストComponent
 *
 * @param users
 */
@Composable
fun UsersDetailsUserListComponent(
    title: String,
    users: List<User>,
    onEndOfListReached: (currentItemCount: Int) -> Unit
) {
    if (users.isEmpty()) {
        UserDetailsEmptyListComponent(stringResource(id = R.string.empty_list))
        return
    }
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(4.dp),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(
                users,
                { it.id }
            ) { user ->
                UserCellComponent(user = user)
            }
        }
        if (listState.isLastVisible()) {
            val currentItemCount = listState.layoutInfo.totalItemsCount
            onEndOfListReached.invoke(currentItemCount)
        }
    }
}

@Composable
private fun UserCellComponent(
    user: User
) {
    Column {
        SpacerVerticalSmall()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SpacerHorizontalSmall()
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.urlAvatar)
                    .crossfade(false)
                    .build(),
                error = painterResource(id = R.drawable.github_mark_dark),
                contentDescription = stringResource(id = R.string.content_description_user_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
            )
            SpacerHorizontalMedium()
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                SpacerVerticalSmall()
                KeyedTextComponent(
                    key = stringResource(id = R.string.id),
                    value = "${user.id}"
                )
                KeyedTextComponent(
                    key = stringResource(id = R.string.name),
                    value = user.userName
                )
                SpacerVerticalSmall()
            }
        }
        SpacerVerticalSmall()
    }
}

@Composable
private fun KeyedTextComponent(
    key: String,
    value: String
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            key,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SpacerHorizontalSmall()
        Text(
            value,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SpacerVerticalSmall() {
    Spacer(Modifier.height(4.dp))
}

@Composable
private fun SpacerHorizontalSmall() {
    Spacer(Modifier.width(4.dp))
}

@Composable
private fun SpacerHorizontalMedium() {
    Spacer(Modifier.width(12.dp))
}