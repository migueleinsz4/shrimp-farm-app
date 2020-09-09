package com.cargill.challenge.shrimp.service

import com.cargill.challenge.shrimp.dto.FarmTotalSizeDto
import com.cargill.challenge.shrimp.dto.PondProj
import com.cargill.challenge.shrimp.repository.FarmRepository
import com.cargill.challenge.shrimp.repository.FarmerRepository
import com.cargill.challenge.shrimp.repository.PondRepository
import com.cargill.challenge.shrimp.util.ApiResponse
import com.cargill.challenge.shrimp.util.ResponseCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ShrimpFarmServiceImpl(private val farmerRepository: FarmerRepository,
                            private val farmRepository: FarmRepository,
                            private val pondRepository: PondRepository
) : ShrimpFarmService {

    @Transactional(readOnly = true)
    override fun <T> findFarmerById(projection: Class<T>, id: Long): ApiResponse<T> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmerRepository.findByIdAndDeleted(projection, id)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findFarmerByUsername(projection: Class<T>, username: String): ApiResponse<T> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmerRepository.findByUsernameAndDeleted(projection, username)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findAllFarmers(projection: Class<T>): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmerRepository.findAllByDeleted(projection)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findFarmById(projection: Class<T>, id: Long): ApiResponse<T> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmRepository.findByIdAndDeleted(projection, id)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findAllFarms(projection: Class<T>): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmRepository.findAllByDeleted(projection)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findAllFarmsByFarmer(projection: Class<T>, idFarmer: Long): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmRepository.findAllByFarmerIdAndDeleted(projection, idFarmer)
        )
    }

    @Transactional(readOnly = true)
    override fun calculateFarmTotalSize(id: Long): ApiResponse<FarmTotalSizeDto> {
        val ponds = this.pondRepository.findAllByFarmIdAndDeleted(PondProj::class.java, id)

        val farmTotalSize = ponds.map { it.size }.fold(BigDecimal.ZERO) {sum, element -> sum + element}

        return ApiResponse(
                ResponseCode.SUCCESS.code,
                FarmTotalSizeDto(
                        id,
                        ponds.size,
                        farmTotalSize
                )
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findPondById(projection: Class<T>, id: Long): ApiResponse<T> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.pondRepository.findByIdAndDeleted(projection, id)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findAllPondsByFarm(projection: Class<T>, idFarm: Long): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.pondRepository.findAllByFarmIdAndDeleted(projection, idFarm)
        )
    }

}