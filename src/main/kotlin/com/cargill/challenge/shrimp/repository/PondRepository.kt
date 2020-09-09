package com.cargill.challenge.shrimp.repository

import com.cargill.challenge.shrimp.model.Pond
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PondRepository : JpaRepository<Pond, Long> {
    fun <T> findByIdAndDeleted(projection: Class<T>, id: Long, deleted: Boolean = false): T
    fun <T> findAllByFarmIdAndDeleted(projection: Class<T>, idFarm: Long, deleted: Boolean = false): MutableList<T>
}