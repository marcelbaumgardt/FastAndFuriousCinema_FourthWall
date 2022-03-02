package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.dto.common.MovieDetail
import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.model.dto.response.MovieDetailResponseStatus
import org.springframework.stereotype.Component

@Component
class MovieDetailResponseMapper {

    fun mapToMovieDetailResponse(movieDetail: MovieDetail?): MovieDetailResponse {
        return if (movieDetail != null) {
            getMovieDetailSuccessfulResponse(movieDetail)
        } else {
            getMovieDetailEmptyResponse()
        }
    }

    fun getMovieDetailSuccessfulResponse(movieDetail: MovieDetail): MovieDetailResponse {
        return MovieDetailResponse(
            MovieDetailResponseStatus.SUCCESS,
            movieDetail
        )
    }

    fun getMovieDetailNoFoundResponse(): MovieDetailResponse {
        return MovieDetailResponse(
            MovieDetailResponseStatus.MOVIE_NOT_FOUND_IN_DATABASE,
        )
    }

    fun getMovieDetailEmptyResponse(): MovieDetailResponse {
        return MovieDetailResponse(
            MovieDetailResponseStatus.EMPTY_RESPONSE,
        )
    }
}