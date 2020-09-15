package com.cargill.challenge.shrimp.controller

import com.cargill.challenge.shrimp.dto.*
import com.cargill.challenge.shrimp.service.ShrimpFarmService
import com.cargill.challenge.shrimp.util.ApiResponse
import com.cargill.challenge.shrimp.util.ResponseCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1.0")
class ShrimpFarmController(private val shrimpFarmService: ShrimpFarmService) {
    /*
        Farmers
     */
    @GetMapping("/farmers")
    fun findFarmers(@RequestParam(required = false) username: String?): ResponseEntity<Any> {
        val result = if (username == null)
            this.shrimpFarmService.findAllFarmers(FarmerProj::class.java)
        else
            this.shrimpFarmService.findFarmerByUsername(FarmerProj::class.java, username)

        return ResponseEntity(
                result,
                HttpStatus.OK
        )
    }

    /*
        Farms
     */
    @PostMapping("/farms")
    fun createFarm(@RequestBody farmDto: FarmDto): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.saveFarm(farmDto),
                HttpStatus.CREATED
        )
    }

    @PutMapping("/farms/{idFarm}")
    fun updateFarm(@PathVariable idFarm: Long, @RequestBody farmDto: FarmDto): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.saveFarm(idFarm, farmDto),
                HttpStatus.OK
        )
    }

    @DeleteMapping("/farms/{idFarm}")
    fun deleteFarm(@PathVariable idFarm: Long): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.deleteFarm(idFarm),
                HttpStatus.OK
        )
    }

    @GetMapping("/farms", "/farms/{idFarm}")
    fun findFarms(@PathVariable(required = false) idFarm: Long?,
                  @RequestParam(required = false) idFarmer: Long?,
                  @RequestParam(required = false) farmName: String?): ResponseEntity<Any> {
        val result = when {
            idFarm != null -> this.shrimpFarmService.findFarmById(FarmProj::class.java, idFarm)
            farmName != null && idFarmer != null -> this.shrimpFarmService.findAllFarmsByNameAndFarmer(FarmProj::class.java, farmName, idFarmer)
            idFarmer != null -> this.shrimpFarmService.findAllFarmsByFarmer(FarmProj::class.java, idFarmer)
            else -> this.shrimpFarmService.findAllFarms(FarmProj::class.java)
        }

        return ResponseEntity(
                result,
                HttpStatus.OK
        )
    }

    @GetMapping("/farms/{idFarm}/size")
    fun calculateFarmTotalSize(@PathVariable idFarm: Long): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.calculateFarmTotalSize(idFarm),
                HttpStatus.OK
        )
    }

    /*
        Ponds
     */
    @PostMapping("/ponds")
    fun createPond(@RequestBody pondDto: PondDto): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.savePond(pondDto),
                HttpStatus.CREATED
        )
    }

    @PutMapping("/ponds/{idPond}")
    fun updatePond(@PathVariable idPond: Long, @RequestBody pondDto: PondDto): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.savePond(idPond, pondDto),
                HttpStatus.OK
        )
    }

    @DeleteMapping("/ponds/{idPond}")
    fun deletePond(@PathVariable idPond: Long): ResponseEntity<Any> {
        return ResponseEntity(
                this.shrimpFarmService.deletePond(idPond),
                HttpStatus.OK
        )
    }

    @GetMapping("/ponds", "/ponds/{idPond}")
    fun findPonds(@PathVariable(required = false) idPond: Long?,
                  @RequestParam(required = false) idFarm: Long?,
                  @RequestParam(required = false) idFarmer: Long?,
                  @RequestParam(required = false) pondName: String?): ResponseEntity<Any> {
        val result = when {
            idPond != null -> this.shrimpFarmService.findPondById(PondProj::class.java, idPond)
            idFarm != null -> this.shrimpFarmService.findAllPondsByFarm(PondProj::class.java, idFarm)
            pondName != null && idFarmer != null -> this.shrimpFarmService.findAllPondsByNameAndFarmer(PondProj::class.java, pondName, idFarmer)
            idFarmer != null -> this.shrimpFarmService.findAllPondsByFarmer(PondProj::class.java, idFarmer)
            else -> return ResponseEntity(
                    ApiResponse(ResponseCode.GENERAL_FAILURE.code, ""),
                    HttpStatus.INTERNAL_SERVER_ERROR
            )
        }

        return ResponseEntity(
                result,
                HttpStatus.OK
        )
    }

}