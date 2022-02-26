package com.marcel.baumgardt.model.dto;

import lombok.Builder;

@Builder
public class MovieDetailResponse {
    MovieDetailResponseStatus status;
    @Builder.Default
    MovieDetail movieDetail = null;
}
