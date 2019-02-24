package com.codependent.micronaut.awslambda.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.codependent.micronaut.awslambda.dto.Ping
import io.micronaut.context.env.Environment
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class PingRepository(private val environment: Environment,
                     private val dynamoDb: DynamoDB,
                     private val dynamoDbMapper: DynamoDBMapper) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getAll(): List<Ping> {
        val pings = mutableListOf<Ping>()
        val scan = dynamoDbMapper.scan(Ping::class.java, DynamoDBScanExpression(),
                DynamoDBMapperConfig.builder()
                        .withTableNameOverride(withTableNameReplacement(getTableName<Ping>()))
                        .build())
        scan.forEach { pings.add(it) }
        return pings
    }

    fun save(ping: Ping): Ping {
        val table = dynamoDb.getTable(getTableName<Ping>())
        val item = Item()
                .withPrimaryKey("id", ping.id)
                .withString("name", ping.name)
        val outcome = table.putItem(item)
        logger.info("savePing({}) - outcome {}", ping, outcome)
        return ping
    }

    private inline fun <reified T> getTableName(): String {
        val originalTableName = (T::class.annotations.find { it is DynamoDBTable } as DynamoDBTable).tableName
        return if (environment.activeNames.isNotEmpty()) {
            originalTableName.replace("dev.", environment.activeNames.iterator().next() + ".")
        } else {
            originalTableName
        }
    }

}
