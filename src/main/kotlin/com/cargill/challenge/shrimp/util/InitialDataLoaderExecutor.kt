package com.cargill.challenge.shrimp.util

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["com.cargill.challenge.shrimp.load-initial-data"], havingValue = "true")
class InitialDataLoaderExecutor(private val initialDataLoader: InitialDataLoader) {
    init {
        this.initialDataLoader.loadInitialData()
    }
}