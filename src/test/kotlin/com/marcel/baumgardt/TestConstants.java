package com.marcel.baumgardt;

import com.google.common.collect.ImmutableSet;

import java.time.OffsetTime;
import java.time.ZoneOffset;

public final class TestConstants {
    public static final String GET_MOVIE_DETAIL_URL = "/api/movie/{movieId}";
    public static final String UPDATE_SHOWINGS_PRICE_URL = "/api/showing/price";
    public static final String UPDATE_SHOWINGS_DATES_URL = "/api/showing/dates";
    public static final String GET_SHOWING_DATES_URL = "/api/showing/dates/{cinemaId}/{movieId}";
    public static final String SUCCESSFUL_CINEMA_NAME = "FastAndFuriousCinema";
    public static final long SUCCESSFUL_CINEMA_ID = 1L;
    public static final String SUCCESSFUL_MOVIE_TITLE = "The Fast and the Furious";
    public static final long SUCCESSFUL_MOVIE_ID = 1L;
    public static final String IMDB_ID = "tt0232500";
    public static final long ERROR_MOVIE_ID = 999L;
    public static final String WRITER = "Ken Li, Gary Scott Thompson, Erik Bergquist";
    public static final ImmutableSet<String> ACTORS = ImmutableSet.of("Paul Walker", "Vin Diesel", "Michelle Rodriguez");
    public static final double AVERAGE_RATING_SCORE = 0.6D;
    public static final int META_SCORE = 58;
    public static final long BOX_OFFICE_VALUE = 144533925L;
    public static final String BOX_OFFICE_CURRENCY = "USD";
    public static final int MONDAY = 1;
    public static final int FRIDAY = 5;
    public static final double NEW_PRICE = 10.5D;
    private static final ZoneOffset BERLIN_ZONE_ID = ZoneOffset.of("+01:00");
    public static final OffsetTime BERLIN_TIME_15_00 = OffsetTime.of(15, 0, 0, 0, BERLIN_ZONE_ID);
    public static final OffsetTime BERLIN_TIME_16_00 = OffsetTime.of(16, 0, 0, 0, BERLIN_ZONE_ID);
}

