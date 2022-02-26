package com.marcel.baumgardt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UpdateShowingPriceRequest {
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be lower than 0")
    private Double price;
    @NotNull(message = "Cinema ID cannot be null")
    private Long cinemaId;
    @NotNull(message = "Movie ID cannot be null")
    private Long movieId;
}
