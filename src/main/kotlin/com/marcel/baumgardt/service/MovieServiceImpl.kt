package com.marcel.baumgardt.service

import com.marcel.baumgardt.model.db.Movie
import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.model.mapper.MovieDetailResponseMapper
import com.marcel.baumgardt.repository.MovieRepository
import com.marcel.baumgardt.service.interfaces.MovieService
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    val movieRepository: MovieRepository,
    val openMovieDatabaseService: OpenMovieDatabaseService,
    val movieDetailResponseMapper: MovieDetailResponseMapper
) : MovieService {

    override fun getMovieDetails(movieId: Long): MovieDetailResponse {
        val movieOpt = movieRepository.findById(movieId)
        return movieOpt
            .map { movie: Movie -> getMovieDetailSuccessfulResponse(movie) }
            .orElseGet { movieDetailResponseMapper.getMovieDetailNoFoundResponse() }
    }

    private fun getMovieDetailSuccessfulResponse(movie: Movie): MovieDetailResponse {
        val movieDetail = openMovieDatabaseService.getMovieDetail(movie.imdbId)
        return movieDetailResponseMapper.getMovieDetailSuccessfulResponse(movieDetail)
    }
}