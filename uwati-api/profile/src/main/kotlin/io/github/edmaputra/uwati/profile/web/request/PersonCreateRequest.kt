package io.github.edmaputra.uwati.profile.web.request

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId

data class PersonCreateRequest(

  val name: String = "",

  val email: String = "",

  val type: PersonType = PersonType.ADMINISTRATOR,

  val address: Map<String, Any> = emptyMap(),

  val phone: String = "",

  val metadata: Map<String, Any> = emptyMap()

) {
  object ModelMapper {
    fun toPerson(from: PersonCreateRequest) = Person(
      ObjectId.get(),
      "",
      from.name,
      from.email,
      from.type,
      from.address,
      from.phone,
      from.metadata
    )
  }
}
