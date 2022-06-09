package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.PagingHelper
import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.response.base.PagingBaseResponse

class GetUserFollowingResponse(
    val userName: String,
    nextPagingHelper: PagingHelper,
    val users: List<User>
) : PagingBaseResponse(nextPagingHelper = nextPagingHelper)