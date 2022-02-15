package com.marcel.baumgardt.model.db

import org.hibernate.validator.constraints.Length
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "cinemas", schema = "dbo")
class Cinema(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_id")
    val id: Long,

    @Length(max = 50)
    @Column(name = "cin_name", nullable = false)
    val name: String,

    @Column(name = "cin_manager_id", nullable = false)
    val managerId: Long,

    //TODO Consider extract insertDate and modificationDate to external class
    /**
     * This field is filled up during insert operation by the database itself (with GETDATE()) and shouldn't be filled manually.
     */
    @Column(name = "cin_insert_date", nullable = false, insertable = false, updatable = false)
    val insertDate: Timestamp,

    /**
     * This field is filled up during insert and update operation by the DB trigger
     */
    @Column(name = "cin_modification_date", nullable = false, insertable = false, updatable = false)
    val modificationDate: Timestamp,
)