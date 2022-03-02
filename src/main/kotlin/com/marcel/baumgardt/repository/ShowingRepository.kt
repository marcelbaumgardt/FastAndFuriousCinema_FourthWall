package com.marcel.baumgardt.repository

import com.marcel.baumgardt.model.db.Showing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.OffsetTime
import javax.transaction.Transactional

@Repository
interface ShowingRepository : JpaRepository<Showing, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
        nativeQuery = true, value = "UPDATE dbo.showings " +
                "SET sho_price = ?1 " +
                "WHERE sho_id in (?2)"
    )
    fun updatePriceOfShowingsByIds(price: Double, showingIds: List<Long>): Int

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
        nativeQuery = true, value = "UPDATE dbo.showings " +
                "SET sho_price = ?1 " +
                "WHERE sho_cin_id = ?2 " +
                "AND sho_mov_id = ?3"
    )
    fun updatePriceOfShowingsByCinemaIdAndMovieId(price: Double, cinemaId: Long, movieId: Long): Int


    @Query(
        nativeQuery = true, value = "SELECT * " +
                "FROM dbo.showings sho " +
                "LEFT JOIN dbo.cinemas cin on cin.cin_id = sho.sho_cin_id " +
                "LEFT JOIN dbo.movies mov on mov.mov_id = sho.sho_mov_id " +
                "WHERE sho.sho_cin_id = ?1 " +
                "AND sho.sho_mov_id = ?2"
    )
    fun getShowingsByCinemaIdAndMovieId(cinemaId: Long, movieId: Long): List<Showing>

    @Query(
        nativeQuery = true, value = "SELECT * " +
                "FROM dbo.showings sho " +
                "WHERE sho.sho_cin_id = ?1 " +
                "AND sho.sho_mov_id = ?2 " +
                "AND sho.sho_day = ?3 " +
                "AND sho.sho_time in (?4)"
    )
    fun getShowingsByCinemaIdAndMovieIdFromDayAndTime(
        cinemaId: Long,
        movieId: Long,
        day: Int,
        times: List<OffsetTime>
    ): List<Showing>

    @Query(
        nativeQuery = true, value = "SELECT count(*) " +
                "FROM dbo.showings sho " +
                "WHERE sho.sho_cin_id = ?1 " +
                "AND sho.sho_mov_id = ?2"
    )
    fun countShowingsByCinemaIdAndMovieId(cinemaId: Long, movieId: Long): Int

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
        nativeQuery = true, value = "DELETE " +
                "FROM dbo.showings sho " +
                "WHERE sho.sho_cin_id = ?1 " +
                "AND sho.sho_mov_id = ?2"
    )
    fun deleteAllByCinemaIdAndMovieId(cinemaId: Long, movieId: Long): Int
}