package com.codependent.micronaut.awslambda.dto

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "dev.ping")
data class Ping(@DynamoDBHashKey var id: Int, var name: String)
