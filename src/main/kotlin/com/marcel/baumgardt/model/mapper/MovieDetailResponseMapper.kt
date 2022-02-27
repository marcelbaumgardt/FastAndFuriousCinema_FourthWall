package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.db.Movie
import com.marcel.baumgardt.model.dto.MovieDetailResponse
import com.marcel.baumgardt.model.dto.MovieDetailResponseStatus
import com.marcel.baumgardt.service.OpenMovieDatabaseService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
abstract class MovieDetailResponseMapper {

    abstract val openMovieDatabaseService: OpenMovieDatabaseService

    fun getMovieDetailSuccessfulResponse(movie: Movie): MovieDetailResponse {
        val movieDetail = openMovieDatabaseService.getMovieDetail(movie.imdbId)

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
}