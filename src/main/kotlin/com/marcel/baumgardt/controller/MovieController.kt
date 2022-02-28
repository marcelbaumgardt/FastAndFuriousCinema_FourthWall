package com.marcel.baumgardt.controller

import com.marcel.baumgardt.model.dto.response.MovieDetailResponse
import com.marcel.baumgardt.service.interfaces.MovieService
import lombok.RequiredArgsConstructor
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Log4j2
@RestController
@RequestMapping(value = ["/api/movie"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RequiredArgsConstructor
class MovieController(
    val movieService: MovieService
) {

    @GetMapping("/{movieId}")
    fun getMovieDetail(
        @PathVariable movieId: Long
    ): ResponseEntity<MovieDetailResponse> {
        log.info("Calling get movie detail endpoint with movieId : {} ", movieId)
        val response = movieService.getMovieDetails(movieId)
        log.info("Successfully called get movie detail endpoint with response: {}", response.toString())
        return ResponseEntity.ok(response)
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}