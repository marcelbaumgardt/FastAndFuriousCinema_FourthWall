package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse;
import com.marcel.baumgardt.model.dto.MovieDetail;
import org.springframework.stereotype.Component;

@Component
public class OpenMovieDatabaseResponseMapper {

    public MovieDetail mapToMovieDetail(OpenMovieDatabaseResponse openMovieDatabaseResponse) {
        return new MovieDetail(
                openMovieDatabaseResponse.getTitle()
        );
    }
}
