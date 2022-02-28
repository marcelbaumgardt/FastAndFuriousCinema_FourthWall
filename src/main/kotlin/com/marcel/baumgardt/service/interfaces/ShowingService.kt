package com.marcel.baumgardt.service.interfaces

import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.response.ShowingDatesResponse
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse

interface ShowingService {
    fun updatePrice(request: UpdateShowingPriceRequest): UpdateShowingResponse
    fun updateDates(request: UpdateShowingDatesRequest): UpdateShowingResponse
    fun getDates(cinemaId: Long, movieId: Long): ShowingDatesResponse
}