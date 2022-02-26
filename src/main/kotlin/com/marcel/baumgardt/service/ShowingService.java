package com.marcel.baumgardt.service;

import com.marcel.baumgardt.model.dto.ShowingDatesResponse;
import com.marcel.baumgardt.model.dto.UpdateShowingDatesRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingPriceRequest;
import com.marcel.baumgardt.model.dto.UpdateShowingResponse;

public interface ShowingService {

    UpdateShowingResponse updatePrice(UpdateShowingPriceRequest request);

    UpdateShowingResponse updateDates(UpdateShowingDatesRequest request);

    ShowingDatesResponse getDates(Long cinemaId, Long movieId);
}
