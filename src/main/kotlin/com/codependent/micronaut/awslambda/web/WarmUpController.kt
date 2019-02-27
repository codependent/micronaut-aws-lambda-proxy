package com.codependent.micronaut.awslambda.web

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory


@Controller("/")
class WarmUpController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get
    fun warmUp(): String {
        logger.info("Warming up")
        return """{"message" : "warming up"}"""
    }

}
