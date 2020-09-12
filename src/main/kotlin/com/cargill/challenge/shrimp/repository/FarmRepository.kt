package com.cargill.challenge.shrimp.repository

import com.cargill.challenge.shrimp.model.Farm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FarmRepository : JpaRepository<Farm, Long> {
    fun <T> findByIdAndDeleted(projection: Class<T>, id: Long, deleted: Boolean = false): T
    fun <T> findAllByDeleted(projection: Class<T>, deleted: Boolean = false): MutableList<T>
    fun <T> findAllByNameContainingIgnoreCaseAndFarmerIdAndDeleted(projection: Class<T>, name: String, idFarmer: Long, deleted: Boolean = false): MutableList<T>
    fun <T> findAllByFarmerIdAndDeleted(projection: Class<T>, idFarmer: Long, deleted: Boolean = false): MutableList<T>
}