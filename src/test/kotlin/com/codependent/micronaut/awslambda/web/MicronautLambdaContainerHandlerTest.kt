package com.codependent.micronaut.awslambda.web

class MicronautLambdaContainerHandlerTest {

/*
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
    }*/

}
