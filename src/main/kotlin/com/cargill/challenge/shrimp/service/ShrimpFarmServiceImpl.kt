package com.cargill.challenge.shrimp.service

import com.cargill.challenge.shrimp.dto.FarmDto
import com.cargill.challenge.shrimp.dto.FarmTotalSizeDto
import com.cargill.challenge.shrimp.dto.PondDto
import com.cargill.challenge.shrimp.dto.PondProj
import com.cargill.challenge.shrimp.model.Farm
import com.cargill.challenge.shrimp.model.Farmer
import com.cargill.challenge.shrimp.model.Pond
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

    /*
        Farmer
     */
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

    /*
        Farm
     */
    @Transactional
    override fun saveFarm(farmDto: FarmDto): ApiResponse<Long> {
        val farm = if (farmDto.id == null) {
            val farmer = this.farmerRepository.findByIdAndDeleted(Farmer::class.java, farmDto.idFarmer!!)
            Farm(farmDto.id, farmDto.name, farmer)
        } else {
            val f =this.farmRepository.findByIdAndDeleted(Farm::class.java, farmDto.id)
            f.name = farmDto.name
            f
        }

        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmRepository.save(farm).id
        )
    }

    @Transactional
    override fun deleteFarm(id: Long): ApiResponse<Long> {
        val farm = this.farmRepository.findByIdAndDeleted(Farm::class.java, id)
        farm.deleted = true
        farm.ponds?.forEach { it.deleted = true }
        this.farmRepository.save(farm)
        return ApiResponse(ResponseCode.SUCCESS.code, id)
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
    override fun <T> findAllFarmsByNameAndFarmer(projection: Class<T>, name: String, idFarmer: Long): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.farmRepository.findAllByNameContainingIgnoreCaseAndFarmerIdAndDeleted(projection, name, idFarmer)
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
    override fun calculateFarmTotalSize(id: Long, areaUnit: String): ApiResponse<FarmTotalSizeDto> {
        val ponds = this.pondRepository.findAllByFarmIdAndDeleted(PondProj::class.java, id)

        val farmTotalSize = ponds.map { it.size }.fold(BigDecimal.ZERO) {sum, element -> sum + element}

        return ApiResponse(
                ResponseCode.SUCCESS.code,
                FarmTotalSizeDto(
                        id,
                        ponds.size,
                        farmTotalSize,
                        areaUnit
                )
        )
    }

    /*
        Pond
     */
    @Transactional
    override fun savePond(pondDto: PondDto): ApiResponse<Long> {
        val pond = if (pondDto.id == null) {
            val farm = this.farmRepository.findByIdAndDeleted(Farm::class.java, pondDto.idFarm!!)
            Pond(pondDto.id, pondDto.name, BigDecimal(pondDto.size), farm)
        } else {
            val p = this.pondRepository.findByIdAndDeleted(Pond::class.java, pondDto.id)
            p.name = pondDto.name
            p.size = BigDecimal(pondDto.size)
            p
        }

        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.pondRepository.save(pond).id
        )
    }

    @Transactional
    override fun deletePond(id: Long): ApiResponse<Long> {
        val pond = this.pondRepository.findByIdAndDeleted(Pond::class.java, id)
        pond.deleted = true
        this.pondRepository.save(pond)
        return ApiResponse(ResponseCode.SUCCESS.code, id)
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

    @Transactional(readOnly = true)
    override fun <T> findAllPondsByNameAndFarmer(projection: Class<T>, name: String, idFarmer: Long): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.pondRepository.findAllByNameContainingIgnoreCaseAndFarmFarmerIdAndDeleted(projection, name, idFarmer)
        )
    }

    @Transactional(readOnly = true)
    override fun <T> findAllPondsByFarmer(projection: Class<T>, idFarmer: Long): ApiResponse<List<T>> {
        return ApiResponse(
                ResponseCode.SUCCESS.code,
                this.pondRepository.findAllByFarmFarmerIdAndDeleted(projection, idFarmer)
        )
    }

}