package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.input.PersonCreateInput
import io.github.edmaputra.uwati.profile.input.PersonUpdateInput
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PersonService {

  fun findAll(): Flux<Person>

  fun findAll(page: Int = 0, size: Int = 20, sort: String = "id", ascending: Boolean, search: String?): Flux<Person>
//
  fun getById(id: String): Mono<Person>
//
  fun create(input: PersonCreateInput)
//
//  fun update(input: PersonUpdateInput): Person
//
  fun delete(id: String)

  fun hardDelete(id: String)

}
