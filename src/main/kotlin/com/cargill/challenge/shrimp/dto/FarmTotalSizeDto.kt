package com.cargill.challenge.shrimp.dto

import java.math.BigDecimal

data class FarmTotalSizeDto(
        val idFarm: Long,
        val numberOfPonds: Int,
        val totalSize: BigDecimal,
        val areaUnit: String
)