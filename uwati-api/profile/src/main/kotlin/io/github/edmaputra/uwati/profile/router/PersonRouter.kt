package io.github.edmaputra.uwati.profile.router

import io.github.edmaputra.uwati.profile.handler.PersonHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class PersonRouter {

  @Bean
  fun personRouterFunction(personHandler: PersonHandler): RouterFunction<ServerResponse>? = router {
    "/persons".nest {
      GET("/{id}") { personHandler.getById(it) }
      DELETE("/{id}") { personHandler.delete(it) }
      method(HttpMethod.GET) { personHandler.getAll(it) }
      accept(APPLICATION_JSON).nest {
        method(HttpMethod.POST) { personHandler.create(it) }
        method(HttpMethod.PUT) { personHandler.update(it) }
      }
    }
  }

}
