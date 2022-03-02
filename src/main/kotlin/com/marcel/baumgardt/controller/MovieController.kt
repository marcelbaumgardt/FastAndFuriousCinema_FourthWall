package com.marcel.baumgardt.controller

import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.model.dto.response.UpdateMovieRateResponse
import com.marcel.baumgardt.service.interfaces.MovieService
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Log4j2
@Validated
@RestController
@RequestMapping(value = ["/api/movie"], produces = [MediaType.APPLICATION_JSON_VALUE])
class MovieController(
    val movieService: MovieService
) {

    @GetMapping("/{movieId}")
    fun getMovieDetail(
        @PathVariable movieId: Long
    ): ResponseEntity<MovieDetailResponse> {
        log.info("Calling get movie detail endpoint with movieId : {} ", movieId)
        val response = movieService.getMovieDetailResponse(movieId)
        log.info("Successfully called get movie detail endpoint with response: {}", response.toString())
        return ResponseEntity.ok(response)
    }


    @PostMapping("/{movieId}")
    fun updateMovieRate(
        @PathVariable movieId: Long,
        @RequestParam(value = "rate") @Min(1) @Max(5) rate: Double
    ): ResponseEntity<UpdateMovieRateResponse> {
        log.info("Calling update movie rate endpoint with movieId : {} ", movieId)
        val response = movieService.updateMovieRate(movieId, rate)
        log.info("Successfully called update movie rate endpoint with response: {}", response.toString())
        return ResponseEntity.ok(response)
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}