package com.cargill.challenge.shrimp.util

import com.cargill.challenge.shrimp.model.Farm
import com.cargill.challenge.shrimp.model.Farmer
import com.cargill.challenge.shrimp.model.Pond
import com.cargill.challenge.shrimp.repository.FarmerRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Component
class InitialDataLoaderImpl(private val farmerRepository: FarmerRepository) : InitialDataLoader {

    @Transactional
    override fun loadInitialData() {
        val jon = Farmer(
                null,
                "Jon Snow",
                "jsnow",
                "cargill"
        )

        val farmJ1 = Farm(
                null,
                "Farm J1",
                jon
        )

        val farmJ2 = Farm(
                null,
                "Farm J2",
                jon
        )

        val pondJ1P1 = Pond(
                null,
                "Pond 1 Farm J1",
                BigDecimal("10"),
                null,
                null,
                null,
                farmJ1
        )

        val pondJ1P2 = Pond(
                null,
                "Pond 2 Farm J1",
                BigDecimal("10"),
                null,
                null,
                null,
                farmJ1
        )

        val sansa = Farmer(
                null,
                "Sansa Stark",
                "sstark",
                "cargill"
        )

        val farmS1 = Farm(
                null,
                "Farm S1",
                sansa
        )

        val farmS2 = Farm(
                null,
                "Farm S2",
                sansa
        )

        val pondS1P1 = Pond(
                null,
                "Pond 1 Farm S1",
                BigDecimal("10"),
                null,
                null,
                null,
                farmS1
        )

        val pondS2P1 = Pond(
                null,
                "Pond 1 Farm S2",
                BigDecimal("10"),
                null,
                null,
                null,
                farmS2
        )

        farmJ1.ponds = mutableListOf(pondJ1P1, pondJ1P2)
        farmS1.ponds = mutableListOf(pondS1P1)
        farmS2.ponds = mutableListOf(pondS2P1)
        jon.farms = mutableListOf(farmJ1, farmJ2)
        sansa.farms = mutableListOf(farmS1, farmS2)

        this.farmerRepository.saveAll(listOf(jon, sansa))
    }

}