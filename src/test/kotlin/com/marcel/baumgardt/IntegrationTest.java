package com.marcel.baumgardt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FastAndFuriousCinemaFouthWallApplication.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WireMockServer localOpenMovieDatabaseConnector;

    @AfterEach
    void resetRequests() {
        localOpenMovieDatabaseConnector.resetRequests();
    }


    protected MvcResult performGetRequest(String url, Object... pathVariables) throws Exception {

        return mockMvc.perform(
                        MockMvcRequestBuilders.get(url, pathVariables)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    protected <T> MvcResult performPutRequest(String url, T request) throws Exception {

        return mockMvc.perform(
                        MockMvcRequestBuilders.put(url)
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


    protected <T> T getResponseObject(MvcResult mvcResult, Class<T> classType) throws JsonProcessingException, UnsupportedEncodingException {
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String actualResponseBody = response.getContentAsString();
        return objectMapper.readValue(actualResponseBody, classType);
    }
}
