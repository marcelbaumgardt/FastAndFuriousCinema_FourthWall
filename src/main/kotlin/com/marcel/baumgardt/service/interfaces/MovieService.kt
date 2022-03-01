package com.marcel.baumgardt.service.interfaces

import com.marcel.baumgardt.model.dto.response.MovieDetailResponse

interface MovieService {
    fun getMovieDetailResponse(movieId: Long): MovieDetailResponse
}