package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.IntegrationTest;
import com.marcel.baumgardt.TestUtils;
import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest;
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest;
import com.marcel.baumgardt.model.dto.response.ShowingDatesResponse;
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse;
import com.marcel.baumgardt.repository.ShowingRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static com.marcel.baumgardt.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShowingControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private ShowingRepository showingRepository;

    @BeforeEach
    void setup() {
        //TODO this approach should be changed
        showingRepository.deleteAll();
        showingRepository.saveAll(TestUtils.Companion.createShowings());
    }

    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    @Test
    void shouldSuccessfullyUpdatePriceOfShowingsAsAdmin() throws Exception {
        UpdateShowingPriceRequest request = TestUtils.Companion.createUpdateShowingPriceRequest();
        int affectedRows = getAffectedRows(request.getCinemaId(), request.getMovieId());
        UpdateShowingResponse expected = TestUtils.Companion.createSuccessfulUpdateShowingResponse(affectedRows);

        MvcResult mvcResult = performPutRequest(UPDATE_SHOWINGS_PRICE_URL, request);
        UpdateShowingResponse actual = getResponseObject(mvcResult, UpdateShowingResponse.class);

        assertEquals(actual, expected);
    }

    @WithMockUser(username = "user", authorities = {"USER"})
    @Test
    void shouldThrowForbiddenExceptionWhenUpdatePriceOfShowings() throws Exception {
        //TODO
    }

    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    @Test
    void shouldSuccessfullyUpdateDatesOfShowings() throws Exception {

        UpdateShowingDatesRequest request = TestUtils.Companion.createSuccessfulUpdateShowingDatesRequest();

        UpdateShowingResponse expected = TestUtils.Companion.createNoEntitiesAffectedShowingResponse();

        MvcResult mvcResult = performPutRequest(UPDATE_SHOWINGS_DATES_URL, request);
        UpdateShowingResponse actual = getResponseObject(mvcResult, UpdateShowingResponse.class);

        assertEquals(actual, expected);
    }

    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    @Test
    void shouldSuccessfullyClearOldUpdateDatesOfShowings() throws Exception {
        UpdateShowingDatesRequest request = TestUtils.Companion.createClearOldSuccessfulUpdateShowingDatesRequest();
        int affectedRows = getAffectedRows(request.getCinemaId(), request.getMovieId());
        UpdateShowingResponse expected = TestUtils.Companion.createSuccessfulUpdateShowingResponse(affectedRows);

        MvcResult mvcResult = performPutRequest(UPDATE_SHOWINGS_PRICE_URL, request);
        UpdateShowingResponse actual = getResponseObject(mvcResult, UpdateShowingResponse.class);

        assertEquals(actual, expected);
    }

    @WithMockUser(username = "user", authorities = {"USER"})
    @Test
    void shouldGerDatesOfShowingsByCinemaIdAndMovieId() throws Exception {
        ShowingDatesResponse expected = TestUtils.Companion.createShowingDatesResponse();

        MvcResult mvcResult = performGetRequest(GET_SHOWING_DATES_URL, SUCCESSFUL_CINEMA_ID, SUCCESSFUL_MOVIE_ID);
        ShowingDatesResponse actual = getResponseObject(mvcResult, ShowingDatesResponse.class);

        assertEquals(actual, expected);
    }

    private int getAffectedRows(Long cinemaId, Long movieId) {
        return showingRepository.countShowingsByCinemaIdAndMovieId(cinemaId, movieId);
    }

}
