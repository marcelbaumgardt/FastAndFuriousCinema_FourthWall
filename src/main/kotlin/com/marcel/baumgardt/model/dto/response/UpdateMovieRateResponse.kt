package com.marcel.baumgardt.model.dto.response

data class UpdateMovieRateResponse(
    val status: UpdateResponseStatus,
    val rate: Double,
    val rateOfMovieAfterUpdate: Double? = null,
)