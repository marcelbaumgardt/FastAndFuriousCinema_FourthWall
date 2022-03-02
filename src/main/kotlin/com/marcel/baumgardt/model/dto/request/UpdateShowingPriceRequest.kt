package com.marcel.baumgardt.model.dto.request

import javax.validation.constraints.Min

data class UpdateShowingPriceRequest(
    val cinemaId: Long,
    val movieId: Long,
    @Min(value = 0)
    val price: Double
)
