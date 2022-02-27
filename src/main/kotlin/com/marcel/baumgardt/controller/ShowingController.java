package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.model.dto.ShowingDatesResponse;
import com.marcel.baumgardt.model.dto.UpdateShowingDatesRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingPriceRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingResponse;
import com.marcel.baumgardt.service.ShowingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/showing")
@RequiredArgsConstructor
public class ShowingController {

    private final ShowingService showingService;

    @PutMapping("/price")
    UpdateShowingResponse updatePriceOfShowings(
            @RequestBody UpdateShowingPriceRequest request
    ) {

        log.debug("Calling update showings price endpoint with request : {} ", request);
        UpdateShowingResponse response = showingService.updatePrice(request);
        log.debug("Successfully called update showings price endpoint with response: {}", response);
        return response;
    }

    @PutMapping("/dates")
    UpdateShowingResponse updateDatesOfShowings(
            @RequestBody UpdateShowingDatesRequest request
    ) {
        log.debug("Calling update dates price endpoint with request : {} ", request);
        UpdateShowingResponse response = showingService.updateDates(request);
        log.debug("Successfully called update dates price endpoint with response: {}", response);
        return response;
    }

    @GetMapping("/dates/{cinemaId}/{movieId}")
    ShowingDatesResponse getDatesOfShowings(
            @PathVariable(value = "cinemaId") Long cinemaId,
            @PathVariable(value = "movieId") Long movieId
    ) {
        log.debug("Calling get dates of showings endpoint by cinemaId : {} and movieId {}", cinemaId, movieId);
        ShowingDatesResponse response = showingService.getDates(cinemaId, movieId);
        log.debug("Successfully called get dates of showings endpoint with response: {}", response);
        return response;
    }
}
