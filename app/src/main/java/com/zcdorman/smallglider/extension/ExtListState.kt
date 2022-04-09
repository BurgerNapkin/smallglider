package com.zcdorman.smallglider.extension

import androidx.compose.foundation.lazy.LazyListState

/**
 * リストの最後の項目が表示しているかどうかのフラグ
 */
fun LazyListState.isLastVisible(): Boolean {
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}