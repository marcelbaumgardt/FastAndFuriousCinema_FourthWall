package com.marcel.baumgardt.model.dto

import lombok.Builder

@Builder
class ShowingDatesResponse(
    private val cinemaId: Long,
    private val cinemaName: String,
    private val movieId: Long,
    private val movieTitle: String,
    private val dates: List<ShowingDate>
)