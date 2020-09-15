package com.cargill.challenge.shrimp.service

import com.cargill.challenge.shrimp.dto.FarmDto
import com.cargill.challenge.shrimp.dto.FarmTotalSizeDto
import com.cargill.challenge.shrimp.dto.PondDto
import com.cargill.challenge.shrimp.util.ApiResponse
import com.cargill.challenge.shrimp.util.AreaUnit

interface ShrimpFarmService {
    // Farmer
    fun <T> findFarmerById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findFarmerByUsername(projection: Class<T>, username: String): ApiResponse<T>
    fun <T> findAllFarmers(projection: Class<T>): ApiResponse<List<T>>
    // Farm
    fun saveFarm(farmDto: FarmDto): ApiResponse<Long>
    fun saveFarm(id: Long, farmDto: FarmDto): ApiResponse<Long>
    fun deleteFarm(id: Long): ApiResponse<Long>
    fun <T> findFarmById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findAllFarms(projection: Class<T>): ApiResponse<List<T>>
    fun <T> findAllFarmsByNameAndFarmer(projection: Class<T>, name: String, idFarmer: Long): ApiResponse<List<T>>
    fun <T> findAllFarmsByFarmer(projection: Class<T>, idFarmer: Long): ApiResponse<List<T>>
    fun calculateFarmTotalSize(id: Long, areaUnit: String = AreaUnit.HECTARE.name): ApiResponse<FarmTotalSizeDto>
    // Pond
    fun savePond(pondDto: PondDto): ApiResponse<Long>
    fun savePond(id: Long, pondDto: PondDto): ApiResponse<Long>
    fun deletePond(id: Long): ApiResponse<Long>
    fun <T> findPondById(projection: Class<T>, id: Long): ApiResponse<T>
    fun <T> findAllPondsByFarm(projection: Class<T>, idFarm: Long): ApiResponse<List<T>>
    fun <T> findAllPondsByNameAndFarmer(projection: Class<T>, name: String, idFarmer: Long): ApiResponse<List<T>>
    fun <T> findAllPondsByFarmer(projection: Class<T>, idFarmer: Long): ApiResponse<List<T>>
}