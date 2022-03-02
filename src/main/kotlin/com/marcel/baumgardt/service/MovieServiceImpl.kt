package com.marcel.baumgardt.service

import com.marcel.baumgardt.model.db.Movie
import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.model.dto.response.UpdateMovieRateResponse
import com.marcel.baumgardt.model.mapper.MovieDetailResponseMapper
import com.marcel.baumgardt.model.mapper.UpdateMovieRateResponseMapper
import com.marcel.baumgardt.repository.MovieRepository
import com.marcel.baumgardt.service.interfaces.MovieService
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    val movieRepository: MovieRepository,
    val openMovieDatabaseService: OpenMovieDatabaseService,
    val movieDetailResponseMapper: MovieDetailResponseMapper,
    val updateMovieRateResponseMapper: UpdateMovieRateResponseMapper
) : MovieService {

    override fun getMovieDetailResponse(movieId: Long): MovieDetailResponse {
        val movieOpt = movieRepository.findById(movieId)
        return if (movieOpt.isPresent) {
            this.getMovieDetailResponse(movieOpt.get())
        } else {
            movieDetailResponseMapper.getMovieDetailNoFoundResponse()
        }
    }

    private fun getMovieDetailResponse(movie: Movie): MovieDetailResponse {
        val movieDetail = openMovieDatabaseService.getMovieDetail(movie.imdbId)
        return movieDetailResponseMapper.mapToMovieDetailResponse(movieDetail)
    }

    override fun updateMovieRate(movieId: Long, rate: Double): UpdateMovieRateResponse {
        val affectedRows = movieRepository.updateMovieRate(movieId, rate)
        return if (affectedRows != 0) {
            val rateOfMovieAfterUpdate = movieRepository.getById(movieId).rate
            updateMovieRateResponseMapper.mapToSuccessfulUpdateMovieRateResponse(rateOfMovieAfterUpdate, rate)
        } else {
            updateMovieRateResponseMapper.mapToNotAffectedUpdateMovieRateResponse(rate)
        }
    }
}