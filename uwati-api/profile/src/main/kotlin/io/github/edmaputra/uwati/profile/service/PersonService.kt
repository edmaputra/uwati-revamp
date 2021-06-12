package io.github.edmaputra.uwati.profile.service

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.input.PersonCreateInput
import io.github.edmaputra.uwati.profile.input.PersonUpdateInput
import org.bson.types.ObjectId

interface PersonService {

  fun findAll(): List<Person>

  fun getById(id: ObjectId): Person

  fun create(input: PersonCreateInput): Person

  fun update(input: PersonUpdateInput): Person

  fun delete(id: ObjectId)

  fun hardDelete(id: ObjectId)

}
