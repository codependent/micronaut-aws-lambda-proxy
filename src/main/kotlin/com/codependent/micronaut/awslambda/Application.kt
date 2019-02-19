package com.codependent.micronaut.awslambda

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.micronaut.awslambda")
                .mainClass(Application.javaClass)
                .start()
    }
}
