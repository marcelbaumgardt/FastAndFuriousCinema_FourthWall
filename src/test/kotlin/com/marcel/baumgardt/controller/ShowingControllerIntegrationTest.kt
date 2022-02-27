package com.marcel.baumgardt.controller

import com.marcel.baumgardt.IntegrationTest
import com.marcel.baumgardt.TestConstants.Companion.GET_SHOWING_DATES_URL
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_CINEMA_ID
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_MOVIE_ID
import com.marcel.baumgardt.TestConstants.Companion.UPDATE_SHOWINGS_DATES_URL
import com.marcel.baumgardt.TestConstants.Companion.UPDATE_SHOWINGS_PRICE_URL
import com.marcel.baumgardt.TestUtils.Companion.createClearOldSuccessfulUpdateShowingDatesRequest
import com.marcel.baumgardt.TestUtils.Companion.createShowingDatesResponse
import com.marcel.baumgardt.TestUtils.Companion.createSuccessfulUpdateShowingDatesRequest
import com.marcel.baumgardt.TestUtils.Companion.createSuccessfulUpdateShowingResponse
import com.marcel.baumgardt.TestUtils.Companion.createUpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.ShowingDatesResponse
import com.marcel.baumgardt.model.dto.UpdateShowingDatesRequest
import com.marcel.baumgardt.model.dto.UpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.UpdateShowingResponse
import com.marcel.baumgardt.repository.ShowingRepository
import lombok.RequiredArgsConstructor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@RequiredArgsConstructor
internal abstract class ShowingControllerIntegrationTest : IntegrationTest() {

    abstract val showingRepository: ShowingRepository

    @Test
    fun shouldSuccessfullyUpdatePriceOfShowings() {

        val request: UpdateShowingPriceRequest = createUpdateShowingPriceRequest()
        val affectedRows = getAffectedRows(request.cinemaId, request.movieId)
        val expected: UpdateShowingResponse = createSuccessfulUpdateShowingResponse(affectedRows)

        val mvcResult = performPutRequest(UPDATE_SHOWINGS_PRICE_URL, request)
        val actual: UpdateShowingResponse = getResponseObject(mvcResult, UpdateShowingResponse::class.java)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun shouldSuccessfullyUpdateDatesOfShowings() {

        val request: UpdateShowingDatesRequest = createSuccessfulUpdateShowingDatesRequest()
        val affectedRows = getAffectedRows(request.cinemaId, request.movieId)
        val expected: UpdateShowingResponse = createSuccessfulUpdateShowingResponse(affectedRows)

        val mvcResult = performPutRequest(UPDATE_SHOWINGS_DATES_URL, request)
        val actual: UpdateShowingResponse = getResponseObject(mvcResult, UpdateShowingResponse::class.java)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun shouldSuccessfullyClearOldUpdateDatesOfShowings() {
        val request: UpdateShowingDatesRequest = createClearOldSuccessfulUpdateShowingDatesRequest()
        val affectedRows = getAffectedRows(request.cinemaId, request.movieId)
        val expected: UpdateShowingResponse = createSuccessfulUpdateShowingResponse(affectedRows)

        val mvcResult = performPutRequest(UPDATE_SHOWINGS_PRICE_URL, request)
        val actual: UpdateShowingResponse = getResponseObject(mvcResult, UpdateShowingResponse::class.java)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun shouldGerDatesOfShowingsByCinemaIdAndMovieId() {
        val expected: ShowingDatesResponse = createShowingDatesResponse()

        val mvcResult = performGetRequest(GET_SHOWING_DATES_URL, SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID)
        val actual: ShowingDatesResponse = getResponseObject(mvcResult, ShowingDatesResponse::class.java)

        Assertions.assertEquals(expected, actual)
    }

    private fun getAffectedRows(cinemaId: Long, movieId: Long): Int {
        return showingRepository.countShowingsByCinemaIdAndMovieId(cinemaId, movieId)
    }
}