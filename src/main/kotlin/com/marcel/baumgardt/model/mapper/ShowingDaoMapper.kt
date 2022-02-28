package com.marcel.baumgardt.model.mapper


import com.marcel.baumgardt.model.dao.ShowingDao
import com.marcel.baumgardt.model.dto.common.ShowingDate
import org.springframework.stereotype.Component
import java.time.OffsetTime
import java.util.stream.Collectors

@Component
class ShowingDaoMapper {

    fun mapToShowingsDao(cinemaId: Long, movieId: Long, dates: List<ShowingDate>): MutableList<ShowingDao> {
        return dates.stream()
            .map { sd: ShowingDate -> getShowingsDao(cinemaId, movieId, sd) }
            .flatMap { dao: List<ShowingDao> -> dao.stream() }
            .collect(Collectors.toList())
    }

    private fun getShowingsDao(cinemaId: Long, movieId: Long, date: ShowingDate): List<ShowingDao> {
        return date.timeList.stream()
            .map { of: OffsetTime -> createShowingDao(cinemaId, movieId, date.day, of) }
            .collect(Collectors.toList())
    }

    private fun createShowingDao(cinemaId: Long, movieId: Long, day: Int, offsetTime: OffsetTime): ShowingDao {
        return ShowingDao(
            cinemaId,
            movieId,
            day,
            offsetTime
        )
    }
}