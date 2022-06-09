package com.zcdorman.smallglider.network.response.base

import com.zcdorman.smallglider.model.data.PagingHelper

open class PagingBaseResponse(val nextPagingHelper: PagingHelper) : BaseResponse()