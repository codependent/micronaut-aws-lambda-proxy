package com.codependent.micronaut.awslambda.web

import com.codependent.micronaut.awslambda.dto.Ping
import com.codependent.micronaut.awslambda.service.PingService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory


@Controller("/ping")
class PingController(private val pingService: PingService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get("/")
    fun getPings(@Header("Host") host: String): List<Ping> {
        logger.info("Host Header {}", host)
        return pingService.getPings()
    }

    @Post("/")
    @Status(HttpStatus.CREATED)
    fun createPing(@Body ping: Ping): Ping {
        logger.info("ping {}", ping)
        return pingService.savePing(ping)
    }
}
