package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.IntegrationTest;
import com.marcel.baumgardt.model.dto.response.MovieDetailResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static com.marcel.baumgardt.TestConstants.GET_MOVIE_DETAIL_URL;
import static com.marcel.baumgardt.TestConstants.SUCCESSFUL_MOVIE_ID;
import static com.marcel.baumgardt.TestUtils.createSuccessfulMovieDetailResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieControllerIntegrationTest extends IntegrationTest {

    @WithMockUser(username = "user", authorities = {"USER"})
    @Test
    void shouldGetSuccessfulMovieDetailsResponseAsUser() throws Exception {
        MovieDetailResponse expected = createSuccessfulMovieDetailResponse();

        MvcResult mvcResult = performGetRequest(GET_MOVIE_DETAIL_URL, SUCCESSFUL_MOVIE_ID);
        MovieDetailResponse actual = getResponseObject(mvcResult, MovieDetailResponse.class);

        assertEquals(actual, expected);
    }

    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    @Test
    void shouldGetSuccessfulMovieDetailsResponseAsAdmin() throws Exception {
        MovieDetailResponse expected = createSuccessfulMovieDetailResponse();

        MvcResult mvcResult = performGetRequest(GET_MOVIE_DETAIL_URL, SUCCESSFUL_MOVIE_ID);
        MovieDetailResponse actual = getResponseObject(mvcResult, MovieDetailResponse.class);

        assertEquals(actual, expected);
    }

    @Test
    void shouldThrowUnauthorizedExceptionWhenGetMovieDetails() {
        //TODO
    }

    @Test
    void shouldNotFoundMovieDetails() {
        //TODO
    }
}
