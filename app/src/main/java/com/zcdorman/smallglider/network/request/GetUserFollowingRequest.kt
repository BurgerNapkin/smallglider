package com.zcdorman.smallglider.network.request

import com.zcdorman.smallglider.model.data.PagingHelper
import com.zcdorman.smallglider.network.request.base.PagingBaseRequest

class GetUserFollowingRequest(
    val userName: String,
    pagingHelper: PagingHelper,
) : PagingBaseRequest(pagingHelper)