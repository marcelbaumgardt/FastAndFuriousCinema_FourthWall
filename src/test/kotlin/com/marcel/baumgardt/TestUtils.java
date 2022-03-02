package com.marcel.baumgardt;

import com.google.common.collect.ImmutableList;
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse;
import com.marcel.baumgardt.model.db.Movie;
import com.marcel.baumgardt.model.db.Showing;
import com.marcel.baumgardt.model.dto.common.MovieDetail;
import com.marcel.baumgardt.model.dto.common.ShowingDate;
import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest;
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest;
import com.marcel.baumgardt.model.dto.response.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static com.marcel.baumgardt.TestConstants.*;

public class TestUtils {

    @NotNull
    public static MovieDetailResponse createSuccessfulMovieDetailResponse() {
        return new MovieDetailResponse(
                MovieDetailResponseStatus.SUCCESS,
                createMovieDetail()
        );
    }

    @NotNull
    public static MovieDetailResponse createEmptyMovieDetailResponse() {
        return new MovieDetailResponse(
                MovieDetailResponseStatus.EMPTY_RESPONSE,
                null
        );
    }

    @NotNull
    public static MovieDetailResponse createNotFoundMovieDetailResponse() {
        return new MovieDetailResponse(
                MovieDetailResponseStatus.MOVIE_NOT_FOUND_IN_DATABASE,
                null
        );
    }

    @NotNull
    public static UpdateShowingResponse createSuccessfulUpdateShowingResponse(int affectedRows) {
        return new UpdateShowingResponse(UpdateResponseStatus.UPDATED, affectedRows);
    }

    @NotNull
    public static UpdateShowingResponse createNoEntitiesAffectedShowingResponse() {
        return new UpdateShowingResponse(UpdateResponseStatus.NO_ENTITIES_AFFECTED, 0);
    }

    @NotNull
    public static UpdateShowingPriceRequest createUpdateShowingPriceRequest() {
        return new UpdateShowingPriceRequest(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, NEW_PRICE);
    }

    @NotNull
    public static UpdateShowingDatesRequest createClearOldSuccessfulUpdateShowingDatesRequest() {
        return new UpdateShowingDatesRequest(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, createShowingDates(), true);
    }

    @NotNull
    public static UpdateShowingDatesRequest createSuccessfulUpdateShowingDatesRequest() {
        return new UpdateShowingDatesRequest(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, createShowingDates(), false);
    }


    @NotNull
    public static ShowingDatesResponse createShowingDatesResponse() {
        return new ShowingDatesResponse(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_CINEMA_NAME, SUCCESSFUL_MOVIE_ID, SUCCESSFUL_MOVIE_TITLE, createShowingDates());
    }

    public static MovieDetail createMovieDetail() {
        return new MovieDetail(
                SUCCESSFUL_MOVIE_TITLE,
                WRITER,
                ACTORS,
                AVERAGE_RATING_SCORE,
                META_SCORE,
                BOX_OFFICE_CURRENCY,
                BOX_OFFICE_VALUE
        );
    }

    public static List<Showing> createShowings() {
        return List.of(
                new Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, MONDAY, BERLIN_TIME_15_00, 0.0),
                new Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, MONDAY, BERLIN_TIME_16_00, 0.0),
                new Showing(SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID, FRIDAY, BERLIN_TIME_16_00, 0.0)
        );
    }

    private static List<ShowingDate> createShowingDates() {
        return ImmutableList.of(
                new ShowingDate(MONDAY, ImmutableList.of(BERLIN_TIME_15_00, BERLIN_TIME_16_00)),
                new ShowingDate(FRIDAY, ImmutableList.of(BERLIN_TIME_16_00))
        );
    }

    public static OpenMovieDatabaseResponse createOpenMovieDatabaseResponse() {
        //TODO
        return null;
    }

    public static Optional<Movie> createPresentMovieOpt() {
        return Optional.of(
                new Movie(
                        SUCCESSFUL_MOVIE_TITLE,
                        IMDB_ID,
                        0.0
                )
        );
    }

    public static Optional<Movie> createEmptyMovieOpt() {
        return Optional.empty();
    }
}

