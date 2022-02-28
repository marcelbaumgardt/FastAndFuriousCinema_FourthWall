package com.marcel.baumgardt.controller

import com.marcel.baumgardt.IntegrationTest
import com.marcel.baumgardt.TestConstants.Companion.ERROR_MOVIE_ID
import com.marcel.baumgardt.TestConstants.Companion.GET_MOVIE_DETAIL_URL
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_MOVIE_ID
import com.marcel.baumgardt.TestUtils.Companion.createNotFoundMovieDetailResponse
import com.marcel.baumgardt.TestUtils.Companion.createSuccessfulMovieDetailResponse
import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import lombok.RequiredArgsConstructor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@RequiredArgsConstructor
internal abstract class MovieControllerIntegrationTest : IntegrationTest() {

    @Test
    fun shouldGetSuccessfulMovieDetails() {
        val expected: MovieDetailResponse = createSuccessfulMovieDetailResponse()

        val mvcResult = performGetRequest(GET_MOVIE_DETAIL_URL, SUCCESSFUL_MOVIE_ID)
        val actual: MovieDetailResponse = getResponseObject(mvcResult, MovieDetailResponse::class.java)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldGetNotFoundGetMovieDetails() {
        val expected: MovieDetailResponse = createNotFoundMovieDetailResponse()

        val mvcResult = performGetRequest(GET_MOVIE_DETAIL_URL, ERROR_MOVIE_ID)
        val actual: MovieDetailResponse = getResponseObject(mvcResult, MovieDetailResponse::class.java)

        assertEquals(expected, actual)
    }
}