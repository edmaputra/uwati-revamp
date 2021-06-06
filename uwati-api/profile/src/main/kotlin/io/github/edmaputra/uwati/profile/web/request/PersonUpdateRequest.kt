package io.github.edmaputra.uwati.profile.web.request

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId

data class PersonUpdateRequest(

  val id: ObjectId,

  val personId: String,

  val name: String = "",

  val email: String = "",

  val type: PersonType = PersonType.ADMINISTRATOR,

  val address: Map<String, Any> = emptyMap(),

  val phone: String = "",

  val metadata: Map<String, Any> = emptyMap()

) {
  object ModelMapper {
    fun toPerson(from: PersonUpdateRequest) = Person(
      from.id,
      from.personId,
      from.name,
      from.email,
      from.type,
      from.address,
      from.phone,
      from.metadata
    )
  }
}
