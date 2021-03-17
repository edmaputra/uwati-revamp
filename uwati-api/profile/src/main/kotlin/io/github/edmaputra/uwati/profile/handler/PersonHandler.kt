package io.github.edmaputra.uwati.profile.handler

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.service.PersonService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PersonHandler(
    private val service: PersonService
) {

    fun listPersons(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(service.listPersons(), Person::class.java)
    }
}