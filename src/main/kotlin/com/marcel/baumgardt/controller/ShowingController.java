package com.marcel.baumgardt.controller;

import com.marcel.baumgardt.model.dto.ShowingDatesResponse;
import com.marcel.baumgardt.model.dto.UpdateShowingDatesRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingPriceRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingResponse;
import com.marcel.baumgardt.service.ShowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/showing/")
@RequiredArgsConstructor
public class ShowingController {

    private final ShowingService showingService;

    @PutMapping("/price")
    List<UpdateShowingResponse> updatePriceOfShowings(
            @RequestBody UpdateShowingPriceRequest request
    ) {
        return new ArrayList<>();
    }

    @PutMapping("/dates")
    List<UpdateShowingResponse> updateDatesOfShowings(
            @RequestBody UpdateShowingDatesRequest request
    ) {
        return new ArrayList<>();
    }

    @GetMapping("/dates/{cinemaId}/{movieId}")
    List<ShowingDatesResponse> getDatesOfShowings(
            @PathVariable(value = "cinemaId") Long cinemaId,
            @PathVariable(value = "movieId") Long movieId
    ) {
        return new ArrayList<>();
    }
}
