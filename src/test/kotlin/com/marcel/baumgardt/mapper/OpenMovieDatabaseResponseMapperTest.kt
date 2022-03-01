package com.marcel.baumgardt.mapper

import com.marcel.baumgardt.model.mapper.OpenMovieDatabaseResponseMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired

@ExtendWith(MockitoExtension::class)
class OpenMovieDatabaseResponseMapperTest {

    @Autowired
    private var openMovieDatabaseResponseMapper: OpenMovieDatabaseResponseMapper = OpenMovieDatabaseResponseMapper()

    @Test()
    fun shouldMapToMovieDetail() {
        //TODO Check mapper
//        val openMovieDatabaseResponse : OpenMovieDatabaseResponse = createOpenMovieDatabaseResponse()
//        val expected : MovieDetail = createMovieDetail()
//
//        val actual = openMovieDatabaseResponseMapper.mapToMovieDetail(openMovieDatabaseResponse)
//
//        assertEquals(actual,expected)
    }
}