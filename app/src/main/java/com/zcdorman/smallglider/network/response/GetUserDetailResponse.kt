package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.DetailedUser
import com.zcdorman.smallglider.network.response.base.BaseResponse

class GetUserDetailResponse(
    val detailedUser: DetailedUser
) : BaseResponse()