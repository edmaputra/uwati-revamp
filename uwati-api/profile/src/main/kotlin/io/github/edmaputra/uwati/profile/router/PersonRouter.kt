package io.github.edmaputra.uwati.profile.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Configuration
class PersonRouter {

    @Bean
    fun personRouterFunction(): RouterFunction<*>? {
        return route(GET("/"),
            {
                ServerResponse.ok().body(
                    Mono.just("Hello World"),
                    String::class.java
                )
            })
    }
}