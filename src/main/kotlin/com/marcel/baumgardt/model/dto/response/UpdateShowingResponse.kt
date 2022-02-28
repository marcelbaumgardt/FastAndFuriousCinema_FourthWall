package com.marcel.baumgardt.model.dto.response


data class UpdateShowingResponse(
    private val status: UpdateShowingResponseStatus,
    private val affectedEntities: Int = 0
)