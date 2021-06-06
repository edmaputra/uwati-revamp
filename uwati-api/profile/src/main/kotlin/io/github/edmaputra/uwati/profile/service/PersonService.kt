package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.bson.types.ObjectId
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PersonService {

  fun findAll(): List<Person>

  fun getById(request: ObjectId): Person

  fun create(request: PersonCreateRequest): Person

  fun update(request: PersonUpdateRequest): Person

  fun delete(request: ObjectId)

}
