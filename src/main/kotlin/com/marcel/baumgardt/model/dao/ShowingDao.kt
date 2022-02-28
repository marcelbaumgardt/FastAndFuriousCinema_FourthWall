package com.marcel.baumgardt.model.dao

import java.time.OffsetTime

class ShowingDao(
    val cinemaId: Long,
    val movieId: Long,
    val day: Int,
    val offsetTime: OffsetTime
)