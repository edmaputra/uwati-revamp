package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.request.PersonUpdateRequest
import org.bson.types.ObjectId

interface PersonService {

  fun findAll(): List<Person>

  fun getById(id: ObjectId): Person

  fun create(request: PersonCreateRequest): Person

  fun update(request: PersonUpdateRequest): Person

  fun delete(id: ObjectId)

  fun hardDelete(id: ObjectId)

}
