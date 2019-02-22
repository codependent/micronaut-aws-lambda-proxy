package com.codependent.micronaut.awslambda.web

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.context.ApplicationContext
import io.micronaut.function.aws.proxy.MicronautLambdaContainerHandler
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpMethod
import io.micronaut.http.MediaType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MicronautLambdaContainerHandlerTest {


    @Test
    fun `should return correctly formed body`() {

        val handler = MicronautLambdaContainerHandler.getAwsProxyHandler(
                ApplicationContext.build()
                )

        val lambdaContext = MockLambdaContext()

        val builder = AwsProxyRequestBuilder("/ping", HttpMethod.POST.toString())
        builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        builder.body("""{"value":"somevalue"}""")

        val response = handler.proxy(builder.build(), lambdaContext)

        println(response)
        assertEquals(201, response.statusCode)

        val objectMapper = ObjectMapper()
        val jsonResponse = objectMapper.writeValueAsString(response)
        println(jsonResponse)
    }

}
