package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.db.Movie;
import com.marcel.baumgardt.model.dto.MovieDetail;
import com.marcel.baumgardt.model.dto.MovieDetailResponse;
import com.marcel.baumgardt.model.dto.MovieDetailResponseStatus;
import com.marcel.baumgardt.service.OpenMovieDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieDetailResponseMapper {

    private final OpenMovieDatabaseService openMovieDatabaseService;

    public MovieDetailResponse getMovieDetailResponse(Movie movie) {
        MovieDetail movieDetail = openMovieDatabaseService.getMovieDetail(movie.getImdbId());

        return MovieDetailResponse.builder()
                .status(MovieDetailResponseStatus.SUCCESS)
                .movieDetail(movieDetail)
                .build();
    }

    public MovieDetailResponse getErrorMovieDetailResponse() {
        return MovieDetailResponse.builder()
                .status(MovieDetailResponseStatus.MOVIE_NOT_FOUND_IN_DATABASE)
                .build();
    }
}
