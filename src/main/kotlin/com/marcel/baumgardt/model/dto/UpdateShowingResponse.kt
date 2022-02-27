package com.marcel.baumgardt.model.dto


class UpdateShowingResponse(
    private val status: UpdateShowingResponseStatus,
    private val affectedEntities: Int = 0
)