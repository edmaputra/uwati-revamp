package io.github.edmaputra.uwati.profile.handler

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.service.PersonService
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PersonHandler(
  private val service: PersonService
) {

  fun getAll(request: ServerRequest): Mono<ServerResponse> {
    return ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(service.getAll(), Person::class.java)
  }

  fun getById(request: ServerRequest): Mono<ServerResponse> {
    return ServerResponse
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(service.getById(Mono.just(request.pathVariable("id"))), Person::class.java)
  }

  fun create(request: ServerRequest): Mono<ServerResponse> {
    val result = service.create(request.bodyToMono(PersonCreateRequest::class.java))
    return ServerResponse.created(request.uri())
      .contentType(MediaType.APPLICATION_JSON)
      .body(result, Person::class.java)
  }

  fun update(request: ServerRequest): Mono<ServerResponse> {
    val result = service.update(request.bodyToMono(PersonUpdateRequest::class.java))
    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(result, Person::class.java)
  }

  fun delete(request: ServerRequest): Mono<ServerResponse> {
    val result = service.delete(Mono.just(request.pathVariable("id")))
    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(result, Person::class.java)
  }
}
