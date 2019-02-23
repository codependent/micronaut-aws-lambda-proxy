package com.codependent.micronaut.awslambda.service

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.codependent.micronaut.awslambda.dto.Ping
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class PingService(private val dynamoDb: DynamoDB, private val dynamoDbMapper: DynamoDBMapper) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getPings(): List<Ping> {
        val pings = mutableListOf<Ping>()
        val scan: PaginatedScanList<Ping> = dynamoDbMapper.scan(Ping::class.java, DynamoDBScanExpression())
        scan.forEach { pings.add(it) }
        return pings
    }

    fun savePing(ping: Ping): Ping {
        val table = dynamoDb.getTable("dev.ping")
        val item = Item()
                .withPrimaryKey("id", ping.id)
                .withString("name", ping.name)
        val outcome = table.putItem(item)
        logger.info("savePing({}) - outcome {}", ping, outcome)
        return ping
    }

}
