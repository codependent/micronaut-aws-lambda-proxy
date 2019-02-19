package com.codependent.micronaut.awslambda.web

import com.codependent.micronaut.awslambda.web.dto.Ping
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory


@Controller("/ping")
class PingController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get("/")
    fun getPing(@Header("Host") host: String): Ping {
        logger.info("Host Header {}", host)
        return Ping("myPing")
    }

    @Post("/")
    @Status(HttpStatus.CREATED)
    fun createPing(@Body ping: Ping): Ping {
        logger.info("ping {}", ping)
        return ping
    }
}
