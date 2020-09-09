package com.cargill.challenge.shrimp.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

fun getJsonString(obj: Any?): String? {
    try {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        return objectMapper.writeValueAsString(obj)
    } catch (e: JsonProcessingException) {
        e.printStackTrace()
    }
    return try {
        obj.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}