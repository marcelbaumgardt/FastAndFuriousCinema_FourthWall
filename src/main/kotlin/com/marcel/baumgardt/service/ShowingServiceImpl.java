package com.marcel.baumgardt.service;

import com.marcel.baumgardt.model.db.Showing;
import com.marcel.baumgardt.model.dto.*;
import com.marcel.baumgardt.model.mapper.ShowingDatesResponseMapper;
import com.marcel.baumgardt.model.mapper.ShowingMapper;
import com.marcel.baumgardt.model.mapper.UpdateShowingResponseMapper;
import com.marcel.baumgardt.repository.ShowingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ShowingServiceImpl implements ShowingService {

    private final ShowingRepository showingRepository;
    private final ShowingMapper showingMapper;
    private final UpdateShowingResponseMapper updateShowingResponseMapper;
    private final ShowingDatesResponseMapper showingDatesResponseMapper;

    @Override
    public UpdateShowingResponse updatePrice(UpdateShowingPriceRequest request) {

        int count = showingRepository.updatePriceOfShowingsByCinemaIdAndMovieId(
                request.getPrice(),
                request.getCinemaId(),
                request.getMovieId()
        );

        return updateShowingResponseMapper.mapToUpdateShowingResponse(count);
    }

    @Override
    @Transactional()
    public UpdateShowingResponse updateDates(UpdateShowingDatesRequest request) {

        List<ShowingDate> dates = request.isClearOld()
                ? removeOldShowingsByCinemaIdAndMovieId(request)
                : selectNotCreatedShowings(request);


        List<Showing> showings = showingMapper.mapToShowings(
                request.getCinemaId(),
                request.getMovieId(),
                dates
        );
        int count = showingRepository.saveAll(showings).size();

        return updateShowingResponseMapper.mapToUpdateShowingResponse(count);
    }


    @Override
    public ShowingDatesResponse getDates(Long cinemaId, Long movieId) {
        List<Showing> showings = showingRepository.getShowingsByCinemaIdAndMovieId(cinemaId, movieId);
        return showingDatesResponseMapper.mapToShowingDatesResponse(cinemaId, movieId, showings);
    }

    private List<ShowingDate> removeOldShowingsByCinemaIdAndMovieId(UpdateShowingDatesRequest request) {
        showingRepository.deleteAllByCinemaIdAndMovieId(
                request.getCinemaId(),
                request.getMovieId()
        );
        return request.getDates();
    }

    private List<ShowingDate> selectNotCreatedShowings(UpdateShowingDatesRequest request) {
        //TODO
        return new ArrayList<>();
    }
}
