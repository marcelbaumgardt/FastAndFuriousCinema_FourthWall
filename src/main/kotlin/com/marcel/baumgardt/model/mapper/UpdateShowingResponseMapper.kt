package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponseStatus
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class UpdateShowingResponseMapper {

    fun mapToUpdateShowingResponse(count: Int): UpdateShowingResponse {
        return if (count == 0) getUpdateShowingResponse(count, UpdateShowingResponseStatus.NO_ENTITIES_AFFECTED)
        else getUpdateShowingResponse(count, UpdateShowingResponseStatus.UPDATED)
    }

    private fun getUpdateShowingResponse(count: Int, status: UpdateShowingResponseStatus): UpdateShowingResponse {
        return UpdateShowingResponse(
            status,
            count
        )
    }
}