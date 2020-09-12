package com.cargill.challenge.shrimp.dto

import org.springframework.beans.factory.annotation.Value

interface FarmProj {
    val id: Long
    val name: String
    val farmerId: Long
        @Value("#{target.farmer.id}")
        get() = 0L
    val farmerName: String
        @Value("#{target.farmer.name}")
        get() = ""
}