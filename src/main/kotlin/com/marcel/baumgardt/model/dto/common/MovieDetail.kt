package com.marcel.baumgardt.model.dto.common

data class MovieDetail(
    val title: String,
    val writer: String,
    val actors: Set<String>,
    val averageRatingScore: Double,
    val metaScore: Int,
    val boxOfficeCurrency: String,
    val boxOfficeValue: Long
)

