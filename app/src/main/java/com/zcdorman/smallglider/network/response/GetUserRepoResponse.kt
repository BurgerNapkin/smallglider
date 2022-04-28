package com.zcdorman.smallglider.network.response

import com.zcdorman.smallglider.model.data.Repo
import com.zcdorman.smallglider.network.response.base.BaseResponse

data class GetUserRepoResponse(
    val repos: List<Repo>,
    val page: Int,
    val perPage: Int
) : BaseResponse()
