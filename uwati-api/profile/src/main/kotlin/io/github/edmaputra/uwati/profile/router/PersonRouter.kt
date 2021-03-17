package io.github.edmaputra.uwati.profile.router

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.handler.PersonHandler
import io.swagger.v3.oas.annotations.enums.ParameterIn
import org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder
import org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder
import org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class PersonRouter {

    @Bean
    fun personRouterFunction(personHandler: PersonHandler): RouterFunction<ServerResponse>? {
        return route().GET(
            "/api/uwati/profile/v0/persons",
            accept(MediaType.APPLICATION_JSON),
            personHandler::listPersons,
            { ops ->
                ops
                    .operationId("listPersons").summary("Listing All Person").description("Listing Persons")
                    .parameter(
                        parameterBuilder()
                            .`in`(ParameterIn.QUERY).name("page").description("Page number")
                            .`in`(ParameterIn.QUERY).name("size").description("Size of Items that want to show")
                    )
                    .response(responseBuilder().responseCode("200").implementationArray(Person::class.java))
            })
            .build()
    }
}