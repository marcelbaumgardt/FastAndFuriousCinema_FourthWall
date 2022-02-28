package com.marcel.baumgardt.model.dto.response

import com.marcel.baumgardt.model.dto.common.ShowingDate

data class ShowingDatesResponse(
    private val cinemaId: Long,
    private val cinemaName: String,
    private val movieId: Long,
    private val movieTitle: String,
    private val dates: List<ShowingDate>
)