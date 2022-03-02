package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.dto.response.UpdateMovieRateResponse
import com.marcel.baumgardt.model.dto.response.UpdateResponseStatus
import org.springframework.stereotype.Component

@Component
class UpdateMovieRateResponseMapper {

    fun mapToSuccessfulUpdateMovieRateResponse(rateOfMovieAfterUpdate: Double?, rate: Double): UpdateMovieRateResponse {
        return UpdateMovieRateResponse(
            UpdateResponseStatus.UPDATED,
            rate,
            rateOfMovieAfterUpdate
        )
    }

    fun mapToNotAffectedUpdateMovieRateResponse(rate: Double): UpdateMovieRateResponse {
        return UpdateMovieRateResponse(
            UpdateResponseStatus.UPDATED,
            rate
        )
    }
}