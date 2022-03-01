package com.marcel.baumgardt.service

import com.marcel.baumgardt.connectror.OpenMovieDatabaseConnector
import com.marcel.baumgardt.exception.OpenMovieDatabaseExecutionException
import com.marcel.baumgardt.model.dto.common.MovieDetail
import com.marcel.baumgardt.model.mapper.OpenMovieDatabaseResponseMapper
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Log4j2
@Service
class OpenMovieDatabaseService(
    openMovieDatabaseConnector: OpenMovieDatabaseConnector,
    openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper
) {
    private val apiKey: String?
    private val apiKeyEnvName = "OMD_API_KEY"

    private val openMovieDatabaseConnector: OpenMovieDatabaseConnector
    private val openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper

    init {
        apiKey = System.getenv(apiKeyEnvName)
        this.openMovieDatabaseConnector = openMovieDatabaseConnector
        this.openMovieDatabaseResponseMapper = openMovieDatabaseResponseMapper
    }

    @Retryable(maxAttemptsExpression = "\${retry.maxAttempts}")
    fun getMovieDetail(imdbId: String): MovieDetail? {
        if (apiKey != null) {
            val response = openMovieDatabaseConnector.getMovieDetails(apiKey, imdbId)

            if (response.body != null) {
                return openMovieDatabaseResponseMapper.mapToMovieDetail(response.body!!)
            } else {
                throw OpenMovieDatabaseExecutionException("Open movie database response is empty")
            }

        } else {
            throw OpenMovieDatabaseExecutionException("Open movie database api key is not provided")
        }
    }

    @Recover
    fun handleGetMovieDetail(exception: RuntimeException, imdbId: String): MovieDetail? {
        log.error("Error occurs get open movie databse details for imdbId {}", imdbId, exception)
        return null
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}