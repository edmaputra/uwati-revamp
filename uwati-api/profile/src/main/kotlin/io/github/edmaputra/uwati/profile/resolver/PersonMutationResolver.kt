package io.github.edmaputra.uwati.profile.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import io.github.edmaputra.uwati.profile.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.request.PersonUpdateRequest
import io.github.edmaputra.uwati.profile.service.PersonService
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class PersonMutationResolver(
  private val service: PersonService
) : GraphQLMutationResolver {

  val personType = mapOf(
    "Administrator" to PersonType.ADMINISTRATOR,
    "Cashier" to PersonType.CASHIER,
    "Doctor" to PersonType.DOCTOR,
    "SuperUser" to PersonType.SUPERUSER,
    "Pharmacist" to PersonType.PHARMACIST
  )

  fun create(
    name: String,
    email: String,
    type: String,
    phone: String,
    address: List<MapEntry>,
    metadata: List<MapEntry>
  ): Person =
    service.create(PersonCreateRequest(name, email, personType[type]!!, address, phone, metadata))

  fun update(
    id: ObjectId,
    name: String,
    email: String,
    phone: String,
    address: List<MapEntry>,
    metadata: List<MapEntry>
  ): Person =
    service.update(PersonUpdateRequest(id, name, email, address, phone, metadata))

  fun delete(id: ObjectId, hardDelete: Boolean): ObjectId {
    if (hardDelete) {
      service.hardDelete(id)
    } else {
      service.delete(id)
    }
    return id
  }


}
