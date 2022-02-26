package com.marcel.baumgardt.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetTime;
import java.util.List;

@Data
@Builder
public class ShowingDate {
    private Integer day;
    private List<OffsetTime> timeList;
}
