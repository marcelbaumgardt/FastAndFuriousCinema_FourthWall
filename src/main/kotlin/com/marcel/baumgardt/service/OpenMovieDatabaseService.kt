package com.marcel.baumgardt.service

import com.marcel.baumgardt.connectror.OpenMovieDatabaseConnector
import com.marcel.baumgardt.model.dto.common.MovieDetail
import com.marcel.baumgardt.model.mapper.OpenMovieDatabaseResponseMapper
import org.springframework.stereotype.Service

@Service
class OpenMovieDatabaseService(
    openMovieDatabaseConnector: OpenMovieDatabaseConnector,
    openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper
) {
    private val apiKey: String
    private val apiKeyEnvName = "OMD_API_KEY"

    private val openMovieDatabaseConnector: OpenMovieDatabaseConnector
    private val openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper

    init {
        apiKey = System.getenv(apiKeyEnvName)
        this.openMovieDatabaseConnector = openMovieDatabaseConnector
        this.openMovieDatabaseResponseMapper = openMovieDatabaseResponseMapper
    }

    fun getMovieDetail(imdbId: String): MovieDetail {
        val response = openMovieDatabaseConnector.getMovieDetails(apiKey, imdbId)
        return openMovieDatabaseResponseMapper.mapToMovieDetail(response.body!!)
    }
}