package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.db.Showing;
import com.marcel.baumgardt.model.dto.ShowingDate;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.OffsetTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowingMapper {

    public List<Showing> mapToShowings(Long cinemaId, Long movieId, List<ShowingDate> dates) {
        return dates.stream()
                .map(d -> getShowings(cinemaId, movieId, d))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @NotNull
    private List<Showing> getShowings(Long cinemaId, Long movieId, ShowingDate date) {
        return date.getTimeList().stream()
                .map(of -> createShowing(cinemaId, movieId, date.getDay(), of))
                .collect(Collectors.toList());
    }

    private Showing createShowing(Long cinemaId, Long movieId, Integer day, OffsetTime offsetTime) {
        return new Showing();
    }
}
