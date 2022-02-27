package com.marcel.baumgardt

import com.google.common.collect.ImmutableSet
import java.time.OffsetTime
import java.time.ZoneId
import java.time.ZoneOffset

class TestConstants {

    companion object {
        const val GET_MOVIE_DETAIL_URL = "/api/movie/{movieId}"
        const val UPDATE_SHOWINGS_PRICE_URL = "/api/showing/price"
        const val UPDATE_SHOWINGS_DATES_URL = "/api/showing/dates"
        const val GET_SHOWING_DATES_URL = "/api/showing/dates/{cinemaId}/{movieId}"
        const val SUCCESSFUL_CINEMA_NAME = "FastAndFuriousCinema"
        const val SUCCESSFUL_CINEMA_ID = 1L
        const val SUCCESSFUL_MOVIE_TITLE = "The Fast and the Furious"
        const val SUCCESSFUL_MOVIE_ID = 1L
        const val ERROR_MOVIE_ID = 999L
        const val WRITER = ""
        val ACTORS = ImmutableSet.of<String>("Paul Walker", "Vin Diesel", "Michelle Rodriguez", "Jordana Brewster")
        const val AVERAGE_RATING_SCORE = 0.6
        const val META_SCORE = 58
        const val BOX_OFFICE_VALUE = 142542950L
        const val BOX_OFFICE_CURRENCY = "USD"
        private val BERLIN_ZONE_ID: ZoneOffset = ZoneOffset.of(ZoneId.of("Europe/Berlin").id)
        const val MONDAY = 1
        const val FRIDAY = 5
        const val NEW_PRICE = 10.50
        val BERLIN_TIME_15_00: OffsetTime = OffsetTime.of(15, 0, 0, 0, BERLIN_ZONE_ID)
        val BERLIN_TIME_16_00: OffsetTime = OffsetTime.of(15, 0, 0, 0, BERLIN_ZONE_ID)
    }

}