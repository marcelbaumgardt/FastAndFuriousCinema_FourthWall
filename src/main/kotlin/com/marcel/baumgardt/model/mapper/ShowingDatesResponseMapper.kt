package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.db.Showing
import com.marcel.baumgardt.model.dto.common.ShowingDate
import com.marcel.baumgardt.model.dto.response.ShowingDatesResponse
import org.springframework.stereotype.Component
import java.time.OffsetTime
import java.util.stream.Collectors.groupingBy

@Component
class ShowingDatesResponseMapper {

    fun mapToShowingDatesResponse(cinemaId: Long, movieId: Long, showings: List<Showing>): ShowingDatesResponse {
        val showingDates = showings.stream().collect(groupingBy { s -> s.day })
            .entries
            .stream()
            .map { e -> mapToShowingDates(e) }
            .toList()

        val cinemaName = showings.first().cinema.name
        val movieTitle = showings.first().movie.title

        return ShowingDatesResponse(
            cinemaId,
            cinemaName,
            movieId,
            movieTitle,
            showingDates
        )
    }

    private fun mapToShowingDates(groupedShowingsEntry: Map.Entry<Int, List<Showing>>): ShowingDate {
        return ShowingDate(
            groupedShowingsEntry.key,
            mapToTimeList(groupedShowingsEntry.value)
        )
    }

    private fun mapToTimeList(showings: List<Showing>): List<OffsetTime> {
        return showings
            .stream()
            .map { s -> s.time }
            .toList()
    }
}