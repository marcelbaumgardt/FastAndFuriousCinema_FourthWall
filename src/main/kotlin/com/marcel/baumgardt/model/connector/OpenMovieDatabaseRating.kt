package com.marcel.baumgardt.model.connector

import com.fasterxml.jackson.annotation.JsonProperty

class OpenMovieDatabaseRating(
    @JsonProperty("Source")
    val source: String,
    @JsonProperty("Value")
    val value: String,
)