package com.marcel.baumgardt.configuration.local

import com.fasterxml.jackson.core.JsonProcessingException
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.google.common.net.HttpHeaders
import lombok.extern.log4j.Log4j2
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.util.StreamUtils.copyToString
import java.nio.charset.Charset.defaultCharset

@Log4j2
@Profile("local")
@Configuration
class LocalFeignConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Throws(
        JsonProcessingException::class
    )
    fun openMovieDatabaseServer(
        @Value("\${request.id.port}") port: Int,
        @Value("\${fafc4w.openMovieDatabase.test.json.url}") testJsonUrl: String,
    ): WireMockServer {
        val requestIdServer = WireMockServer(options().dynamicPort())
        requestIdServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo(""))
                .willReturn(
                    WireMock.aResponse()
                        .withBody(
                            copyToString(
                                LocalFeignConfiguration::class.java.getResourceAsStream(testJsonUrl),
                                defaultCharset()
                            )
                        )
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
        )
        return requestIdServer
    }
}