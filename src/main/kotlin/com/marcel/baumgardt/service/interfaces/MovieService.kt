package com.marcel.baumgardt.service.interfaces

import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.model.dto.response.UpdateMovieRateResponse

interface MovieService {
    fun getMovieDetailResponse(movieId: Long): MovieDetailResponse
    fun updateMovieRate(movieId: Long, rate: Double): UpdateMovieRateResponse
}