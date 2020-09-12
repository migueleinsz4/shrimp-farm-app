package com.cargill.challenge.shrimp.dto

import org.springframework.beans.factory.annotation.Value
import java.math.BigDecimal

interface PondProj {
    val id: Long
    val name: String
    val size: BigDecimal
    val areaUnit: String
    val farmId: Long
        @Value("#{target.farm.id}")
        get() = 0L
    val farmName: String
        @Value("#{target.farm.name}")
        get() = ""
}