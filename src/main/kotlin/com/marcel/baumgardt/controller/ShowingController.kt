package com.marcel.baumgardt.controller

import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.response.ShowingDatesResponse
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse
import com.marcel.baumgardt.service.interfaces.ShowingService
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Log4j2
@RestController
@RequestMapping(value = ["/api/showings"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ShowingController(
    val showingService: ShowingService
) {

    @PutMapping("/price")
    fun updatePriceOfShowings(
        @RequestBody request: UpdateShowingPriceRequest
    ): ResponseEntity<UpdateShowingResponse> {
        log.info("Calling update showings price endpoint with request : {} ", request)
        val response = showingService.updatePrice(request)
        log.info("Successfully called update showings price endpoint with response: {}", response)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/dates")
    fun updateDatesOfShowings(
        @RequestBody request: UpdateShowingDatesRequest
    ): ResponseEntity<UpdateShowingResponse> {
        log.info("Calling update dates price endpoint with request : {} ", request)
        val response = showingService.updateDates(request)
        log.info("Successfully called update dates price endpoint with response: {}", response)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/dates/{cinemaId}/{movieId}")
    fun getDatesOfShowings(
        @PathVariable(value = "cinemaId") cinemaId: Long,
        @PathVariable(value = "movieId") movieId: Long
    ): ResponseEntity<ShowingDatesResponse> {
        log.info(
            "Calling get dates of showings endpoint by cinemaId : {} and movieId {}",
            cinemaId,
            movieId
        )
        val response = showingService.getDates(cinemaId, movieId)
        log.info("Successfully called get dates of showings endpoint with response: {}", response.toString())
        return ResponseEntity.ok(response)
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}