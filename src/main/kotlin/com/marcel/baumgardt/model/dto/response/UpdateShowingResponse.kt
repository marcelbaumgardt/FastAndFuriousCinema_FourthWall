package com.marcel.baumgardt.model.dto.response


data class UpdateShowingResponse(
    val status: UpdateShowingResponseStatus,
    val affectedEntities: Int = 0
)