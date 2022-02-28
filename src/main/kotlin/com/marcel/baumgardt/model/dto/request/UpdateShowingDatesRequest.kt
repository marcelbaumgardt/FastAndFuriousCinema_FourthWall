package com.marcel.baumgardt.model.dto.request

import com.marcel.baumgardt.model.dto.common.ShowingDate

data class UpdateShowingDatesRequest(
    val cinemaId: Long,
    val movieId: Long,
    val dates: List<ShowingDate> = emptyList(),
    val clearOld: Boolean = false
)