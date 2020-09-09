package com.cargill.challenge.shrimp.service

import com.cargill.challenge.shrimp.dto.FarmTotalSizeDto
import com.cargill.challenge.shrimp.util.ApiResponse

interface ShrimpFarmService {
    // Farmer
    fun <T> findFarmerById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findFarmerByUsername(projection: Class<T>, username: String): ApiResponse<T>
    fun <T> findAllFarmers(projection: Class<T>): ApiResponse<List<T>>
    // Farm
    fun <T> findFarmById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findAllFarms(projection: Class<T>): ApiResponse<List<T>>
    fun <T> findAllFarmsByFarmer(projection: Class<T>, idFarmer: Long): ApiResponse<List<T>>
    fun calculateFarmTotalSize(id: Long): ApiResponse<FarmTotalSizeDto>
    // Pond
    fun <T> findPondById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findAllPondsByFarm(projection: Class<T>, idFarm: Long): ApiResponse<List<T>>
}