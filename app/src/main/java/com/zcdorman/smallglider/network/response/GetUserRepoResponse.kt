package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.PagingHelper
import com.zcdorman.smallglider.model.data.Repo
import com.zcdorman.smallglider.network.response.base.PagingBaseResponse

class GetUserRepoResponse(
    val userName: String,
    nextPagingHelper: PagingHelper,
    val repos: List<Repo>
) : PagingBaseResponse(nextPagingHelper = nextPagingHelper)
