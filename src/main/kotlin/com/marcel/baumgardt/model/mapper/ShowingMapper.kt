package com.marcel.baumgardt.model.mapper

import com.marcel.baumgardt.model.dao.ShowingDao
import com.marcel.baumgardt.model.db.Showing
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class ShowingMapper {

    fun mapToShowings(showingsDao: List<ShowingDao>): List<Showing> {
        return showingsDao.stream()
            .map { dao -> mapToShowing(dao) }
            .collect(Collectors.toUnmodifiableList())
    }

    private fun mapToShowing(showingDao: ShowingDao): Showing {
        return Showing(
            showingDao.cinemaId,
            showingDao.movieId,
            showingDao.day,
            showingDao.offsetTime
        )
    }
}