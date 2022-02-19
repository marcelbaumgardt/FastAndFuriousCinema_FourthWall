package com.marcel.baumgardt.repository;

import com.marcel.baumgardt.model.db.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Long, Movie> {
}