package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.model.dto.MovieDetail;
import com.marcel.baumgardt.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("movieId")
    MovieDetail getMovieDetail(
            @PathVariable(value = "movieId") Long movieId
    ) {
        return new MovieDetail("test");
    }
}
