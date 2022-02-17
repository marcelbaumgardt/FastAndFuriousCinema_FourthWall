package com.marcel.baumgardt.connectror

import com.marcel.baumgardt.configuration.FeignConfiguration
import com.marcel.baumgardt.model.connector.OpenMovieDatabaseResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(
    value = "openMovieDatabaseRestConnector",
    url = "\${fafc4w.openMovieDatabase.details.url}",
    configuration = [FeignConfiguration::class]
)
interface OpenMovieDatabaseConnector {

    @GetMapping("/")
    fun getMovieDetails(
        @RequestParam("apikey") apikey: String,
        @RequestParam("i") imdbId: String,
    ): ResponseEntity<OpenMovieDatabaseResponse>
}
