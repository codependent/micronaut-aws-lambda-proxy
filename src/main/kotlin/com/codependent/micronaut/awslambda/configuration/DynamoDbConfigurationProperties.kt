package com.codependent.micronaut.awslambda.configuration

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("dynamodb")
data class DynamoDbConfigurationProperties(var tablePrefix: String?)
