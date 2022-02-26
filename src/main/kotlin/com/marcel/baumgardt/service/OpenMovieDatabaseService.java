package com.marcel.baumgardt.service;

import com.marcel.baumgardt.connectror.OpenMovieDatabaseConnector;
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse;
import com.marcel.baumgardt.model.dto.MovieDetail;
import com.marcel.baumgardt.model.mapper.OpenMovieDatabaseResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenMovieDatabaseService {

    private final OpenMovieDatabaseConnector openMovieDatabaseConnector;
    private final OpenMovieDatabaseResponseMapper openMovieDatabaseResponseMapper;

    //TODO add max daily limit
    //TODO add retryable
    public MovieDetail getMovieDetail(String imdbId) {

        String key = "e4f33820";
        ResponseEntity<OpenMovieDatabaseResponse> response = openMovieDatabaseConnector.getMovieDetails(key, imdbId);
        return openMovieDatabaseResponseMapper.mapToMovieDetail(response.getBody());
    }
}
