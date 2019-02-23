package com.codependent.micronaut.awslambda.service

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import com.codependent.micronaut.awslambda.dto.Ping
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*
import javax.inject.Inject


@MicronautTest(packages = [
    "com.codependent.micronaut.awslambda.service",
    "com.codependent.micronaut.awslambda.configuration"])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PingServiceIntegrationTests {

    @Inject
    private lateinit var amazonDynamoDb: AmazonDynamoDB

    @Inject
    private lateinit var pingService: PingService

    private var tableInitialized = false

    @BeforeEach
    fun beforeEach() {
        println(amazonDynamoDb)
        if (!tableInitialized) {
            val tableName = "dev.ping"
            val hashKeyName = "id"
            createTable(amazonDynamoDb, tableName, hashKeyName)
            tableInitialized = true
        }
    }

    @Test
    fun shouldReturnNoPings() {
        val pings = pingService.getPings()
        assertEquals(0, pings.size)
    }

    @Test
    fun shouldCreateAndReturnPings() {
        pingService.savePing(Ping(1, "myping"))
        pingService.savePing(Ping(2, "myping2"))
        pingService.savePing(Ping(1, "myping1"))
        val pings = pingService.getPings()
        assertEquals(2, pings.size)
    }

    private fun createTable(ddb: AmazonDynamoDB, tableName: String, hashKeyName: String): CreateTableResult {
        val attributeDefinitions = ArrayList<AttributeDefinition>()
        attributeDefinitions.add(AttributeDefinition(hashKeyName, ScalarAttributeType.N))

        val ks = ArrayList<KeySchemaElement>()
        ks.add(KeySchemaElement(hashKeyName, KeyType.HASH))

        val provisionedthroughput = ProvisionedThroughput(1000L, 1000L)

        val request = CreateTableRequest()
                .withTableName(tableName)
                .withAttributeDefinitions(attributeDefinitions)
                .withKeySchema(ks)
                .withProvisionedThroughput(provisionedthroughput)

        return ddb.createTable(request)
    }
}
