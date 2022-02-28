package com.marcel.baumgardt.model.dto.response

import com.marcel.baumgardt.model.dto.common.MovieDetail

data class MovieDetailResponse(
    val status: MovieDetailResponseStatus,
    val movieDetail: MovieDetail? = null
)