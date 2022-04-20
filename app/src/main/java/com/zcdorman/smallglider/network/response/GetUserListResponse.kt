package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.User
import com.zcdorman.smallglider.network.response.base.BaseResponse

class GetUserListResponse(
    val userList: List<User>
) : BaseResponse() {}