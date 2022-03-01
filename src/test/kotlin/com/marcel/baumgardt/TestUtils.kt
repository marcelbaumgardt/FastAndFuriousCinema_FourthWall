package com.marcel.baumgardt

import com.google.common.collect.ImmutableList
import com.marcel.baumgardt.TestConstants.Companion.ACTORS
import com.marcel.baumgardt.TestConstants.Companion.AVERAGE_RATING_SCORE
import com.marcel.baumgardt.TestConstants.Companion.BERLIN_TIME_15_00
import com.marcel.baumgardt.TestConstants.Companion.BERLIN_TIME_16_00
import com.marcel.baumgardt.TestConstants.Companion.BOX_OFFICE_CURRENCY
import com.marcel.baumgardt.TestConstants.Companion.BOX_OFFICE_VALUE
import com.marcel.baumgardt.TestConstants.Companion.FRIDAY
import com.marcel.baumgardt.TestConstants.Companion.IMDB_ID
import com.marcel.baumgardt.TestConstants.Companion.META_SCORE
import com.marcel.baumgardt.TestConstants.Companion.MONDAY
import com.marcel.baumgardt.TestConstants.Companion.NEW_PRICE
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_CINEMA_ID
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_CINEMA_NAME
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_MOVIE_ID
import com.marcel.baumgardt.TestConstants.Companion.SUCCESSFUL_MOVIE_TITLE
import com.marcel.baumgardt.TestConstants.Companion.WRITER
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse
import com.marcel.baumgardt.model.db.Movie
import com.marcel.baumgardt.model.db.Showing
import com.marcel.baumgardt.model.dto.common.MovieDetail
import com.marcel.baumgardt.model.dto.common.ShowingDate
import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.response.*
import lombok.RequiredArgsConstructor
import java.util.*

@RequiredArgsConstructor

abstract class TestUtils {


    companion object {

        fun createSuccessfulMovieDetailResponse(): MovieDetailResponse {
            return MovieDetailResponse(
                MovieDetailResponseStatus.SUCCESS,
                createMovieDetail()
            )
        }

        fun createEmptyMovieDetailResponse(): MovieDetailResponse {
            return MovieDetailResponse(
                MovieDetailResponseStatus.EMPTY_RESPONSE
            )
        }

        fun createNotFoundMovieDetailResponse(): MovieDetailResponse {
            return MovieDetailResponse(
                MovieDetailResponseStatus.MOVIE_NOT_FOUND_IN_DATABASE
            )
        }

        fun createSuccessfulUpdateShowingResponse(affectedRows: Int): UpdateShowingResponse {
            return UpdateShowingResponse(
                UpdateShowingResponseStatus.UPDATED,
                affectedRows
            )
        }

        fun createNoEntitiesAffectedShowingResponse(): UpdateShowingResponse {
            return UpdateShowingResponse(
                UpdateShowingResponseStatus.NO_ENTITIES_AFFECTED,
                0
            )
        }

        fun createUpdateShowingPriceRequest(): UpdateShowingPriceRequest {
            return UpdateShowingPriceRequest(
                SUCCESSFUL_CINEMA_ID,
                SUCCESSFUL_MOVIE_ID,
                NEW_PRICE
            )
        }

        fun createClearOldSuccessfulUpdateShowingDatesRequest(): UpdateShowingDatesRequest {
            return UpdateShowingDatesRequest(
                SUCCESSFUL_CINEMA_ID,
                SUCCESSFUL_MOVIE_ID,
                createShowingDates(),
                true
            )
        }

        fun createSuccessfulUpdateShowingDatesRequest(): UpdateShowingDatesRequest {
            return UpdateShowingDatesRequest(
                SUCCESSFUL_CINEMA_ID,
                SUCCESSFUL_MOVIE_ID,
                createShowingDates()
            )
        }

        private fun createShowingDates(): List<ShowingDate> {
            return ImmutableList.of(
                ShowingDate(MONDAY, ImmutableList.of(BERLIN_TIME_15_00, BERLIN_TIME_16_00)),
                ShowingDate(FRIDAY, ImmutableList.of(BERLIN_TIME_16_00))
            )
        }

        fun createShowingDatesResponse(): ShowingDatesResponse {
            return ShowingDatesResponse(
                SUCCESSFUL_CINEMA_ID,
                SUCCESSFUL_CINEMA_NAME,
                SUCCESSFUL_MOVIE_ID,
                SUCCESSFUL_MOVIE_TITLE,
                createShowingDates()
            )
        }

        fun createMovieDetail(): MovieDetail {
            return MovieDetail(
                SUCCESSFUL_MOVIE_TITLE,
                WRITER,
                ACTORS,
                AVERAGE_RATING_SCORE,
                META_SCORE,
                BOX_OFFICE_CURRENCY,
                BOX_OFFICE_VALUE
            )
        }

        fun createShowings(): List<Showing> {
            return listOf(
                Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, MONDAY, BERLIN_TIME_15_00),
                Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, MONDAY, BERLIN_TIME_16_00),
                Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, FRIDAY, BERLIN_TIME_16_00)
            )
        }

        fun createOpenMovieDatabaseResponse(): OpenMovieDatabaseResponse? {
            //TODO
            return null
        }

        fun createPresentMovieOpt(): Optional<Movie> {
            return Optional.of(
                Movie(
                    SUCCESSFUL_MOVIE_TITLE,
                    IMDB_ID,
                    0.0
                )
            )
        }

        fun createEmptyMovieOpt(): Optional<Movie> {
            return Optional.empty()
        }
    }
}