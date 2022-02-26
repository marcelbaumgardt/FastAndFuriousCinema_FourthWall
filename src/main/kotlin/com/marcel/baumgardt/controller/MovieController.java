package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.model.dto.MovieDetailResponse;
import com.marcel.baumgardt.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("movieId")
    MovieDetailResponse getMovieDetail(
            @PathVariable(value = "movieId") Long movieId
    ) {
        log.debug("movieId : {}", movieId);
        MovieDetailResponse response = movieService.getMovieDetails(movieId);
        log.debug("response : {}", response);
        return response;
    }
}
