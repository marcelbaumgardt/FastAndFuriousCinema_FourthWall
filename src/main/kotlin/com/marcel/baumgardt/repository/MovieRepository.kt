package com.marcel.baumgardt.repository

import com.marcel.baumgardt.model.db.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
        nativeQuery = true, value = "UPDATE dbo.movies " +
                "SET mov_rate = " +
                "CASE " +
                "WHEN mov_rate is null THEN ?2 " +
                "ELSE (mov_rate + ?2) / 2 " +
                "END " +
                "WHERE mov_id = ?1"
    )
    fun updateMovieRate(movieId: Long, rate: Double): Int
}