package com.marcel.baumgardt.connector;

import com.marcel.baumgardt.configuration.local.LocalFeignConfiguration;
import com.marcel.baumgardt.model.dto.MovieDetail;
import com.marcel.baumgardt.service.OpenMovieDatabaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("local")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LocalFeignConfiguration.class})
public class OpenMovieDatabaseConnectorIntegrationTest {
    
    @Autowired
    private OpenMovieDatabaseService openMovieDatabaseService;

    @Test
    void shouldGetOpenMovieDatabaseConnector() {
        MovieDetail movieDetail = openMovieDatabaseService.getMovieDetail();
        String title = movieDetail.getTitle();
    }
}
