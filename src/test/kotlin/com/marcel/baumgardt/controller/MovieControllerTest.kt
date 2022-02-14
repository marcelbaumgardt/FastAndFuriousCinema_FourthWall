package com.marcel.baumgardt.controller

import com.marcel.baumgardt.IntegrationTest
import org.junit.jupiter.api.Test

internal class MovieControllerTest : IntegrationTest() {
    //private final WireMockServer localOpenMovieDatabaseConnector;

    @Test
    fun shouldGetMovieDetails() {

        //Create movieId
        //Create MovieDetailResponse
        //Mock localOpenMovieDatabaseConnector
        //Send Request
        //Assert if response is equal MovieDetailResponse
    }

    @Test
    fun shouldNotGetMovieDetails() {

        //Create wrong movieId
        //Mock localOpenMovieDatabaseConnector
        //Send Request
        //Assert if method throw custom exception
    }
}