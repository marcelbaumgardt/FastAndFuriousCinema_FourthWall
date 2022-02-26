package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.db.Showing;
import com.marcel.baumgardt.model.dto.ShowingDate;
import com.marcel.baumgardt.model.dto.ShowingDatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class ShowingDatesResponseMapper {
    public ShowingDatesResponse mapToShowingDatesResponse(Long cinemaId, Long movieId, List<Showing> showings) {
        List<ShowingDate> showingDates = showings.stream()
                .collect(groupingBy(Showing::getDay))
                .entrySet()
                .stream()
                .map(this::mapToShowingDates)
                .collect(Collectors.toList());

        return ShowingDatesResponse.builder()
                .cinemaId(cinemaId)
                .movieId(movieId)
                .dates(showingDates)
                .build();
    }

    private ShowingDate mapToShowingDates(Map.Entry<Integer, List<Showing>> groupedShowingsEntry) {
        return ShowingDate.builder()
                .day(groupedShowingsEntry.getKey())
                .timeList(mapToTimeList(groupedShowingsEntry.getValue()))
                .build();
    }

    private List<OffsetTime> mapToTimeList(List<Showing> showings) {
        return showings.stream()
                .map(Showing::getTime)
                .collect(Collectors.toList());
    }
}
