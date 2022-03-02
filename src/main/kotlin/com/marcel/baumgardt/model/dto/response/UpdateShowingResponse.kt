package com.marcel.baumgardt.model.dto.response


data class UpdateShowingResponse(
    val status: UpdateResponseStatus,
    val affectedEntities: Int = 0
)