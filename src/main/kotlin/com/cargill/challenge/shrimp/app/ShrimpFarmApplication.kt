package com.cargill.challenge.shrimp.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(scanBasePackages = ["com.cargill.challenge.shrimp"])
@EnableJpaRepositories(basePackages = ["com.cargill.challenge.shrimp.repository"])
@EntityScan(basePackages = ["com.cargill.challenge.shrimp.model"])
@EnableTransactionManagement
class ShrimpFarmApplication

fun main(args: Array<String>) {
	runApplication<ShrimpFarmApplication>(*args)
}