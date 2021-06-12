package io.github.edmaputra.uwati.profile.request

import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId

data class PersonCreateRequest(

  val name: String = "",

  val email: String = "",

  val phone: String = "",

  val type: PersonType = PersonType.ADMINISTRATOR,

  val address: List<MapEntry> = emptyList(),

  val metadata: List<MapEntry> = emptyList(),

  val username: String = ""

) {
  object ModelMapper {

    fun toPerson(from: PersonCreateRequest) = Person(
      ObjectId.get(),
      "",
      from.name,
      from.email,
      from.phone,
      from.type,
      from.address,
      from.metadata,
      from.username
    )
  }
}
