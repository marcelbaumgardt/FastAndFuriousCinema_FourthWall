package com.marcel.baumgardt.model.dto.common

import io.swagger.annotations.ApiModelProperty
import java.time.OffsetTime
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class ShowingDate(
    @Min(0)
    @Max(6)
    val day: Int,
    @ApiModelProperty(example = "[13:30:00+01:00,13:40:00+01:00]")
    val timeList: List<OffsetTime>
)