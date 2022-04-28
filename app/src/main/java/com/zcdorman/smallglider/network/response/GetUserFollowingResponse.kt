package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.response.base.BaseResponse

class GetUserFollowingResponse(
    val users: List<User>,
    val page: Int,
    val perPage: Int
) : BaseResponse()