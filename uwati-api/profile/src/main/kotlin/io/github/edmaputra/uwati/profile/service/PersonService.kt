package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import reactor.core.publisher.Flux

interface PersonService {

    fun listPersons(): Flux<Person>

}