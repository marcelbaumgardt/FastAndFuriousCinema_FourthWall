package com.marcel.baumgardt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateShowingDatesRequest {
    private boolean clearOld;
    private Long cinemaId;
    private Long movieId;
    private List<ShowingDate> dates;
}
