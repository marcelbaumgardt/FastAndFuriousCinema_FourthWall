package com.marcel.baumgardt.service

import com.marcel.baumgardt.model.dao.ShowingDao
import com.marcel.baumgardt.model.db.Showing
import com.marcel.baumgardt.model.dto.common.ShowingDate
import com.marcel.baumgardt.model.dto.request.UpdateShowingDatesRequest
import com.marcel.baumgardt.model.dto.request.UpdateShowingPriceRequest
import com.marcel.baumgardt.model.dto.response.ShowingDatesResponse
import com.marcel.baumgardt.model.dto.response.UpdateShowingResponse
import com.marcel.baumgardt.model.mapper.ShowingDaoMapper
import com.marcel.baumgardt.model.mapper.ShowingDatesResponseMapper
import com.marcel.baumgardt.model.mapper.ShowingMapper
import com.marcel.baumgardt.model.mapper.UpdateShowingResponseMapper
import com.marcel.baumgardt.repository.ShowingRepository
import com.marcel.baumgardt.service.interfaces.ShowingService
import lombok.RequiredArgsConstructor
import lombok.extern.log4j.Log4j2
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetTime
import java.util.stream.Collectors

@Log4j2
@Service
@RequiredArgsConstructor
class ShowingServiceImpl(
    val showingMapper: ShowingMapper,
    val showingDaoMapper: ShowingDaoMapper,
    val showingRepository: ShowingRepository,
    val showingDatesResponseMapper: ShowingDatesResponseMapper,
    val updateShowingResponseMapper: UpdateShowingResponseMapper,
) : ShowingService {

    override fun updatePrice(request: UpdateShowingPriceRequest): UpdateShowingResponse {
        val count = showingRepository.updatePriceOfShowingsByCinemaIdAndMovieId(
            request.price,
            request.cinemaId,
            request.movieId
        )
        return updateShowingResponseMapper.mapToUpdateShowingResponse(count)
    }

    //TODO
    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateDates(request: UpdateShowingDatesRequest): UpdateShowingResponse {
        val dates = if (request.clearOld) removeOldShowingsByCinemaIdAndMovieId(request)
        else selectNotCreatedShowings(request)

        val showingsDao: List<ShowingDao> = showingDaoMapper.mapToShowingsDao(request.cinemaId, request.movieId, dates)

        val count = showingRepository.saveAll(
            showingMapper.mapToShowings(showingsDao)
        ).size

        return updateShowingResponseMapper.mapToUpdateShowingResponse(count)
    }

    override fun getDates(cinemaId: Long, movieId: Long): ShowingDatesResponse {
        val showings = showingRepository.getShowingsByCinemaIdAndMovieId(cinemaId, movieId)
        return showingDatesResponseMapper.mapToShowingDatesResponse(cinemaId, movieId, showings)
    }

    //TODO
    private fun removeOldShowingsByCinemaIdAndMovieId(request: UpdateShowingDatesRequest): List<ShowingDate> {
        showingRepository.deleteAllByCinemaIdAndMovieId(
            request.cinemaId,
            request.movieId
        )
        return request.dates
    }

    //TODO
    private fun selectNotCreatedShowings(request: UpdateShowingDatesRequest): List<ShowingDate> {
        val datesToCreate: MutableList<ShowingDate> = mutableListOf()
        for (date in request.dates) {
            val showings =
                showingRepository.getShowingsByCinemaIdAndMovieIdFromDayAndTime(
                    request.cinemaId,
                    request.movieId,
                    date.day,
                    date.timeList
                )

            val timesToCreate = date.timeList.stream()
                .filter { oft: OffsetTime -> !showings.stream().anyMatch { s: Showing -> s.time == oft } }
                .collect(Collectors.toList())

            if (timesToCreate.isNotEmpty()) {
                datesToCreate.add(
                    ShowingDate(date.day, timesToCreate)
                )
            }
        }
        return datesToCreate
    }
}