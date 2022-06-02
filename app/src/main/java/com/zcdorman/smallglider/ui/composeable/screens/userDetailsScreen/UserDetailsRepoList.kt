package com.zcdorman.smallglider.ui.composeable.screens.userDetailsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zcdorman.smallglider.R
import com.zcdorman.smallglider.extension.isLastVisible
import com.zcdorman.smallglider.model.data.Repo


/**
 * ユーザー詳細のRepoリストのComponent
 *
 * @param repos
 */
@Composable
fun UserDetailsRepoListComponent(
    repos: List<Repo>,
    onEndOfListReached: (currentItemCount: Int) -> Unit
) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.repo),
            modifier = Modifier
                .padding(4.dp),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        val scrollState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = scrollState
        ) {
            items(
                items = repos,
                key = { it.id }
            ) { repo ->
                CellComponent(repo = repo)
            }
        }
        if (listState.isLastVisible()) {
            val currentItemCount = listState.layoutInfo.totalItemsCount
            onEndOfListReached.invoke(currentItemCount)
        }
    }
}

@Composable
private fun CellComponent(
    repo: Repo
) {
    Column {
        SpacerVerticalSmall()
        KeyedTextComponent(
            key = stringResource(id = R.string.name),
            value = repo.name
        )
        KeyedTextComponent(
            key = stringResource(id = R.string.description),
            value = repo.description ?: stringResource(
                id = R.string.no_info
            )
        )
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