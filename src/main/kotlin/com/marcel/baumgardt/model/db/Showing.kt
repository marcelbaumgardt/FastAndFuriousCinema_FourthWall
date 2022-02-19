package com.marcel.baumgardt.model.db

import java.sql.Timestamp
import java.time.OffsetTime
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min


@Entity
@Table(name = "showings", schema = "dbo")
class Showing(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private val id: Long,

    @JoinColumn(name = "sho_cin_id", referencedColumnName = "cin_id", insertable = false, updatable = false)
    @ManyToOne
    private val cinema: Cinema,

    @Column(name = "sho_cin_id")
    private val cinemaId: Long,

    @JoinColumn(name = "sho_mov_id", referencedColumnName = "mov_id", insertable = false, updatable = false)
    @ManyToOne
    private val movie: Movie,

    @Column(name = "sho_mov_id")
    private val movieId: Long,

    @Min(0)
    @Column(name = "sho_price", nullable = false)
    val price: Double,

    @Min(0)
    @Max(6)
    @Column(name = "sho_day", nullable = false)
    val day: Int,

    @Column(name = "sho_time", nullable = false)
    val time: OffsetTime,

    /**
     * This field is filled up during insert operation by the database itself (with GETDATE()) and shouldn't be filled manually.
     */
    @Column(name = "sho_insert_date", nullable = false, insertable = false, updatable = false)
    val insertDate: Timestamp,

    /**
     * This field is filled up during insert and update operation by the DB trigger
     */
    @Column(name = "sho_modification_date", nullable = false, insertable = false, updatable = false)
    val modificationDate: Timestamp,
)