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
@RestController("/api/showing/")
@RequiredArgsConstructor
public class ShowingController {

    private final ShowingService showingService;

    @PutMapping("/price")
    UpdateShowingResponse updatePriceOfShowings(
            @RequestBody UpdateShowingPriceRequest request
    ) {
        log.debug("request : {}", request);
        UpdateShowingResponse response = showingService.updatePrice(request);
        log.debug("response : {}", response);
        return response;
    }

    @PutMapping("/dates")
    UpdateShowingResponse updateDatesOfShowings(
            @RequestBody UpdateShowingDatesRequest request
    ) {
        log.debug("request : {}", request);
        UpdateShowingResponse response = showingService.updateDates(request);
        log.debug("response : {}", response);
        return response;
    }

    @GetMapping("/dates/{cinemaId}/{movieId}")
    ShowingDatesResponse getDatesOfShowings(
            @PathVariable(value = "cinemaId") Long cinemaId,
            @PathVariable(value = "movieId") Long movieId
    ) {
        log.debug("cinemaId : {} , movieId : {}", cinemaId, movieId);
        ShowingDatesResponse response = showingService.getDates(cinemaId, movieId);
        log.debug("response : {}", response);
        return response;
    }
}
