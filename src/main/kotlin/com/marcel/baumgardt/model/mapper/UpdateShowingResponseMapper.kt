package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.dto.response.UpdateResponseStatus
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse
import org.springframework.stereotype.Component

@Component
class UpdateShowingResponseMapper {

    fun mapToUpdateShowingResponse(count: Int): UpdateShowingResponse {
        return if (count == 0) getUpdateShowingResponse(count, UpdateResponseStatus.NO_ENTITIES_AFFECTED)
        else getUpdateShowingResponse(count, UpdateResponseStatus.UPDATED)
    }

    private fun getUpdateShowingResponse(count: Int, status: UpdateResponseStatus): UpdateShowingResponse {
        return UpdateShowingResponse(
            status,
            count
        )
    }
}