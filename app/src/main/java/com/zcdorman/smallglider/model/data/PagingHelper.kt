package com.zcdorman.smallglider.model.data

data class PagingHelper(val page: Int, val hasNext: Boolean = true, val perPage: Int = 20)