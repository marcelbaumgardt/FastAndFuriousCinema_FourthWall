package com.marcel.baumgardt.model.dto

import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class UpdateShowingDatesRequest(
    val cinemaId: Long,
    val movieId: Long,
    val dates: List<ShowingDate> = emptyList(),
    val clearOld: Boolean? = false
)