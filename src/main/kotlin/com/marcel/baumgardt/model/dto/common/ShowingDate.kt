package com.marcel.baumgardt.model.dto.common

import io.swagger.annotations.ApiModelProperty
import java.time.OffsetTime

data class ShowingDate(
    val day: Int,
    @ApiModelProperty(example = "[13:30:00+01:00,13:40:00+01:00]")
    val timeList: List<OffsetTime>
)