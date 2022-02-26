package com.marcel.baumgardt.service;

import com.marcel.baumgardt.model.dto.MovieDetailResponse;

public interface MovieService {
    MovieDetailResponse getMovieDetails(Long movieId);
}
