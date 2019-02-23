package com.codependent.micronaut.awslambda.configuration

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import javax.inject.Singleton

@Factory
class DynamoDbTestConfiguration {

    @Replaces(value = AmazonDynamoDB::class, factory = DynamoDbConfiguration::class)
    @Singleton
    @Bean
    fun dynamoDbTestClient(): AmazonDynamoDB {
        return DynamoDBEmbedded.create().amazonDynamoDB()
    }
}
