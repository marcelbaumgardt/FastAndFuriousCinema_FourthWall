package com.marcel.baumgardt.model.dto

class MovieDetail(
    val title: String,
    val writer: String,
    val actors: Set<String>,
    val averageRatingScore: Double,
    val metaScore: Int,
    val boxOfficeValue: Long,
    val boxOfficeCurrency: String,
)

