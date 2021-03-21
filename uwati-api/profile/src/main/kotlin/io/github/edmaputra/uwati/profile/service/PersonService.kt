package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PersonService {

  fun getAll(): Flux<Person>

  fun getById(request: Mono<String>): Mono<Person>

  fun create(request: Mono<PersonCreateRequest>): Mono<Person>

  fun update(request: Mono<PersonUpdateRequest>): Mono<Person>

  fun delete(request: Mono<String>): Mono<Person>

}
