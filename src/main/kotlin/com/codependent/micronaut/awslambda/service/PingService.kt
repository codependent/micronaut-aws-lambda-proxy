package com.codependent.micronaut.awslambda.service

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.codependent.micronaut.awslambda.dto.Ping
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class PingService {

    private val dynamoDB: DynamoDB
    private val dynamoDBMapper: DynamoDBMapper
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        val client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build()
        dynamoDB = DynamoDB(client)
        dynamoDBMapper = DynamoDBMapper(client)
    }

    fun getPings(): List<Ping> {
        val pings = mutableListOf<Ping>()
        val scan: PaginatedScanList<Ping> = dynamoDBMapper.scan(Ping::class.java, DynamoDBScanExpression())
        scan.forEach { pings.add(it) }
        return pings
    }

    fun savePing(ping: Ping): Ping {
        val table = dynamoDB.getTable("dev.ping")
        val item = Item()
                .withPrimaryKey("id", ping.id)
                .withString("name", ping.name)
        val outcome = table.putItem(item)
        logger.info("savePing({}) - outcome {}", ping, outcome)
        return ping
    }

}
