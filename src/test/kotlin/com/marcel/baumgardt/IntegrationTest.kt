package com.marcel.baumgardt

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import lombok.RequiredArgsConstructor
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.IOException
import java.nio.charset.StandardCharsets

@ActiveProfiles("local")
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [FastAndFuriousCinemaFouthWallApplication::class])
@RequiredArgsConstructor
abstract class IntegrationTest {

    protected abstract val mockMvc: MockMvc
    protected abstract val objectMapper: ObjectMapper

    protected abstract val localOpenMovieDatabaseConnector: WireMockServer

    @AfterEach
    fun setup() {
        localOpenMovieDatabaseConnector.resetRequests()
    }

    @Throws(Exception::class)
    protected open fun performGetRequest(url: String, vararg pathVariable: Any): MvcResult {

        return mockMvc.perform(
            MockMvcRequestBuilders.get(url, pathVariable)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }

    @Throws(Exception::class)
    protected open fun <T> performPutRequest(url: String, request: T): MvcResult {

        return mockMvc.perform(
            MockMvcRequestBuilders.put(url)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
    }

    @Throws(IOException::class)
    protected open fun <T> getResponseObject(mvcResult: MvcResult, classType: Class<T>?): T {
        val response = mvcResult.response
        response.characterEncoding = StandardCharsets.UTF_8.name()
        val actualResponseBody = response.contentAsString
        return objectMapper.readValue(actualResponseBody, classType)
    }
}