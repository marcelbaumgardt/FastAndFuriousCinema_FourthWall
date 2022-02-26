package com.marcel.baumgardt.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ShowingDatesResponse {
    private Long cinemaId;
    private Long movieId;
    private List<ShowingDate> dates;
}
