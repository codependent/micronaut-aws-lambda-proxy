package com.codependent.micronaut.awslambda.service

import com.codependent.micronaut.awslambda.dto.Ping
import com.codependent.micronaut.awslambda.repository.PingRepository
import javax.inject.Singleton


@Singleton
class PingService(private val pingRepository: PingRepository) {

    fun getPings(): List<Ping> {
        return pingRepository.getAll()
    }

    fun savePing(ping: Ping): Ping {
        return pingRepository.save(ping)
    }

}
