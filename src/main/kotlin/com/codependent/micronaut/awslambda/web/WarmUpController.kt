package com.codependent.micronaut.awslambda.web

import com.codependent.micronaut.awslambda.dto.Ping
import com.codependent.micronaut.awslambda.service.PingService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory


@Controller("/")
class WarmUpController(private val pingService: PingService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Get
    fun warmUp(): Ping? {
        logger.info("Warming up")
        return pingService.getPing(1)
    }

}
