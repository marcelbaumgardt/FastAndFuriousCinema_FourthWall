package com.marcel.baumgardt.repository

import com.marcel.baumgardt.model.db.Cinema
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CinemaRepository : JpaRepository<Cinema, Long>