package com.marcel.baumgardt.repository;

import com.marcel.baumgardt.model.db.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "UPDATE sho " +
                    "SET sho.sho_price = ?1" +
                    "FROM dbo.showings WITH(index(sho_pk), nolock) " +
                    "WHERE sho.sho_id in (?2) ")
    int updatePriceOfShowingsByIds(Double price, List<Long> showingIds);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "UPDATE sho " +
                    "SET sho.sho_price = ?1" +
                    "FROM dbo.showings WITH(index(sho_cin_mov_id), nolock) " +
                    "WHERE sho.sho_cin_id = ?2) " +
                    "AND sho.sho_mov_id = ?3")
    int updatePriceOfShowingsByCinemaIdAndMovieId(Double price, Long cinemaId, Long movieId);

    @Query(nativeQuery = true,
            value = "SELECT sho " +
                    "FROM dbo.showings WITH(index(sho_cin_mov_id), nolock) " +
                    "WHERE sho.sho_cin_id = ?1) " +
                    "AND sho.sho_mov_id = ?2")
    List<Showing> getShowingsByCinemaIdAndMovieId(Long cinemaId, Long movieId);

    @Query(nativeQuery = true,
            value = "SELECT count(sho) " +
                    "FROM dbo.showings WITH(index(sho_cin_mov_id), nolock) " +
                    "WHERE sho.sho_cin_id = ?1) " +
                    "AND sho.sho_mov_id = ?2")
    int countShowingsByCinemaIdAndMovieId(Long cinemaId, Long movieId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "DELETE sho " +
                    "FROM dbo.showings WITH(index(sho_cin_mov_id), nolock) " +
                    "WHERE sho.sho_cin_id = ?1) " +
                    "AND sho.sho_mov_id = ?2")
    int deleteAllByCinemaIdAndMovieId(Long cinemaId, Long movieId);
}
