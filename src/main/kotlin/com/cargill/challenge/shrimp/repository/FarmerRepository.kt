package com.cargill.challenge.shrimp.repository

import com.cargill.challenge.shrimp.model.Farmer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FarmerRepository : JpaRepository<Farmer, Long> {
    fun <T> findByIdAndDeleted(projection: Class<T>, id: Long, deleted: Boolean = false): T
    fun <T> findByUsernameAndDeleted(projection: Class<T>, username: String, deleted: Boolean = false): T
    fun <T> findAllByDeleted(projection: Class<T>, deleted: Boolean = false): MutableList<T>
}