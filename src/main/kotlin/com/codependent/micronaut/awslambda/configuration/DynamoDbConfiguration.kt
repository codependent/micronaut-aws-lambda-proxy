package com.codependent.micronaut.awslambda.configuration

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class DynamoDbConfiguration {

    @Singleton
    @Bean
    fun dynamoDbClient(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build()
    }

    @Singleton
    @Bean
    fun dynamoDb(dynamoDbClient: AmazonDynamoDB): DynamoDB {
        return DynamoDB(dynamoDbClient)
    }

    @Singleton
    @Bean
    fun dynamoDbMapper(dynamoDbClient: AmazonDynamoDB): DynamoDBMapper {
        return DynamoDBMapper(dynamoDbClient)
    }

}
