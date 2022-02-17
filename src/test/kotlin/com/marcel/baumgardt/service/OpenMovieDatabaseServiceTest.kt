package com.marcel.baumgardt.service

import com.marcel.baumgardt.connectror.OpenMovieDatabaseConnector
import com.marcel.baumgardt.model.mapper.OpenMovieDatabaseResponseMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class OpenMovieDatabaseServiceTest {

    @Mock
    lateinit var openMovieDatabaseRestConnector: OpenMovieDatabaseConnector

    @Mock
    lateinit var openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper

    private val openMovieDatabaseService: OpenMovieDatabaseService =
        OpenMovieDatabaseService(openMovieDatabaseRestConnector, openMovieDatabaseResponseMapper)

    @Test
    fun shouldGetOpenMovieDatabaseResponse() {
        val movieDetail = openMovieDatabaseService.movieDetail

        val length = movieDetail.title.length
    }

    @Test
    fun shouldGetOpenMovieDatabaseResponseAfterRetry() {
    }

    @Test
    fun shouldNotGetOpenMovieDatabaseResponseAfterRetry() {
    }
}