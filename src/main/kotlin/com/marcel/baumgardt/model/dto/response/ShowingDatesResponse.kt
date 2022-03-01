package com.marcel.baumgardt.model.dto.response

import com.marcel.baumgardt.model.dto.common.ShowingDate

data class ShowingDatesResponse(
    val cinemaId: Long,
    val cinemaName: String,
    val movieId: Long,
    val movieTitle: String,
    val dates: List<ShowingDate>
)