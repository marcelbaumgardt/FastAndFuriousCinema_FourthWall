package com.marcel.baumgardt.model.dto.response

data class TechnicalErrorResponse(
    val status: TechnicalErrorStatus,
    val message: String
)