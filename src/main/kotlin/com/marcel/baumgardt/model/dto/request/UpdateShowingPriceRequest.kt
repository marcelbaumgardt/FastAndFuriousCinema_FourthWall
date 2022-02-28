package com.marcel.baumgardt.model.dto.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class UpdateShowingPriceRequest(
    @NotNull(message = "Cinema ID cannot be null")
    val cinemaId: Long,
    @NotNull(message = "Movie ID cannot be null")
    val movieId: Long,
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be lower than 0")
    val price: Double
)
