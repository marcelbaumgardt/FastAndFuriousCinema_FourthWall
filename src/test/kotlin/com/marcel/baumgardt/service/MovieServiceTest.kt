package com.marcel.baumgardt.service

import com.marcel.baumgardt.TestConstants.IMDB_ID
import com.marcel.baumgardt.TestConstants.SUCCESSFUL_MOVIE_ID
import com.marcel.baumgardt.TestUtils
import com.marcel.baumgardt.TestUtils.*
import com.marcel.baumgardt.model.mapper.MovieDetailResponseMapper
import com.marcel.baumgardt.repository.MovieRepository
import com.marcel.baumgardt.service.interfaces.MovieService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MovieServiceTest(
    @Mock val movieRepository: MovieRepository,
    @Mock val openMovieDatabaseService: OpenMovieDatabaseService,
    @Mock val movieDetailResponseMapper: MovieDetailResponseMapper
) {

    private val movieService: MovieService =
        MovieServiceImpl(movieRepository, openMovieDatabaseService, movieDetailResponseMapper)

    @Test()
    fun shouldSuccessfulEmptyMovieDetailsResponse() {
        val movieDetail = createMovieDetail()
        val expected = createSuccessfulMovieDetailResponse()
        Mockito.`when`(movieRepository.findById(SUCCESSFUL_MOVIE_ID)).thenReturn(TestUtils.createPresentMovieOpt())
        Mockito.`when`(openMovieDatabaseService.getMovieDetail(IMDB_ID)).thenReturn(movieDetail)
        Mockito.`when`(movieDetailResponseMapper.getMovieDetailSuccessfulResponse(movieDetail)).thenReturn(expected)

        val actual = movieService.getMovieDetailResponse(SUCCESSFUL_MOVIE_ID)

        assertEquals(expected, actual)
    }

    @Test()
    fun shouldGetNotFoundMovieDetailsResponse() {

        val expected = createNotFoundMovieDetailResponse()
        Mockito.`when`(movieRepository.findById(SUCCESSFUL_MOVIE_ID)).thenReturn(TestUtils.createEmptyMovieOpt())
        Mockito.`when`(movieDetailResponseMapper.getMovieDetailNoFoundResponse()).thenReturn(expected)

        val actual = movieService.getMovieDetailResponse(SUCCESSFUL_MOVIE_ID)

        assertEquals(expected, actual)
    }
}