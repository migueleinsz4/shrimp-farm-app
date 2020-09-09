package com.cargill.challenge.shrimp.dto

import org.springframework.beans.factory.annotation.Value

interface FarmProj {
    val id: Long
    val name: String
    val idFarmer: Long?
        @Value("#{target.farmer.id}")
        get() = null
}