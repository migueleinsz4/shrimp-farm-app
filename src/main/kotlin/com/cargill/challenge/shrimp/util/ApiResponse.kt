package com.cargill.challenge.shrimp.util

import java.io.Serializable

class ApiResponse<T> (
        val code: String,
        val content: T? = null
) : Serializable