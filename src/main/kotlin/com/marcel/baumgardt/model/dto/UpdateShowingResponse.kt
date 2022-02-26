package com.marcel.baumgardt.model.dto;


import lombok.Builder;

@Builder
public class UpdateShowingResponse {
    private UpdateShowingResponseStatus status;
    private int affectedEntities;
}
