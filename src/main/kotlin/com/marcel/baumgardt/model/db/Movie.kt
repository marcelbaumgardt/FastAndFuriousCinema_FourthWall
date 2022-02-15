package com.marcel.baumgardt.model.db

import org.hibernate.validator.constraints.Length
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@Table(name = "movies", schema = "dbo")
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id")
    val id: Long,

    //TODO Check length
    @Length(max = 50)
    @Column(name = "mov_title", nullable = false)
    val title: String,

    //TODO Check length
    @Length(max = 50)
    @Column(name = "mov_imdb_id")
    val imdbId: String,

    @Min(1)
    @Max(5)
    @Column(name = "mov_rate")
    val rate: Double,

    /**
     * This field is filled up during insert operation by the database itself (with GETDATE()) and shouldn't be filled manually.
     */
    @Column(name = "mov_insert_date", nullable = false, insertable = false, updatable = false)
    val insertDate: Timestamp,

    /**
     * This field is filled up during insert and update operation by the DB trigger
     */
    @Column(name = "mov_modification_date", nullable = false, insertable = false, updatable = false)
    val modificationDate: Timestamp,
)