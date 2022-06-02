package com.zcdorman.smallglider.ui.composeable.screens.userDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.model.data.DetailedUser

/**
 * ユーザー詳細Component
 *
 * @param user
 * @param onBackPressed
 */
@Composable
fun UserDetailDetailsComponent(
    user: DetailedUser,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        //header
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
        SpacerVerticalSmall()

        //basic info
        Row(
            modifier = Modifier
                .height(88.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.urlAvatar)
                    .crossfade(false)
                    .build(),
                error = painterResource(id = R.drawable.github_mark_dark),
                contentDescription = stringResource(id = R.string.content_description_user_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
            SpacerHorizontalMedium()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                KeyedTextComponentHorizontal(
                    key = stringResource(id = R.string.name),
                    value = user.nameLogin
                )
                KeyedTextComponentHorizontal(
                    key = stringResource(id = R.string.id),
                    value = "${user.userId}"
                )
            }
        }
        SpacerVerticalSmall()

        //other info
        user.bio?.also {
            KeyedTextComponentVertical(
                key = stringResource(id = R.string.bio),
                value = it
            )
            SpacerVerticalSmall()
        }
    }
}

@Composable
private fun KeyedTextComponentHorizontal(
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
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SpacerHorizontalSmall()
        Text(
            value,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun KeyedTextComponentVertical(
    key: String,
    value: String
) {
    Column {
        Text(
            key,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            value,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SpacerVerticalSmall() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun SpacerHorizontalSmall() {
    Spacer(Modifier.width(4.dp))
}

@Composable
private fun SpacerHorizontalMedium() {
    Spacer(Modifier.width(4.dp))
}