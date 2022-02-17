package com.marcel.baumgardt.configuration

import com.marcel.baumgardt.connectror.OpenMovieDatabaseConnector
import feign.Feign
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignConfiguration(@param:Value("\${fafc4w.openMovieDatabase.details.url}") private val url: String) {

    @Bean
    fun openMovieDatabaseRestConnector(): OpenMovieDatabaseConnector {
        return Feign.builder()
            .contract(SpringMvcContract())
            .target(OpenMovieDatabaseConnector::class.java, url)
    }
}