package com.marcel.baumgardt.service;

import com.marcel.baumgardt.model.db.Movie;
import com.marcel.baumgardt.model.dto.MovieDetailResponse;
import com.marcel.baumgardt.model.mapper.MovieDetailResponseMapper;
import com.marcel.baumgardt.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieDetailResponseMapper movieDetailResponseMapper;

    @Override
    public MovieDetailResponse getMovieDetails(Long movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);

        return movieOpt
                .map(movieDetailResponseMapper::getMovieDetailResponse)
                .orElseGet(movieDetailResponseMapper::getErrorMovieDetailResponse);
    }
}
