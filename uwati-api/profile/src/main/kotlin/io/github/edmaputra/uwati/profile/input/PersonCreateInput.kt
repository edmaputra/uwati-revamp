package io.github.edmaputra.uwati.profile.input

import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import java.io.Serializable

data class PersonCreateInput (

//  @field:NotEmpty
  val name: String,

//  @field:NotEmpty
  val email: String,

  val phone: String,

//  @field:NotNull
  val type: PersonType,

  val address: List<MapEntry> = emptyList(),

  val metadata: List<MapEntry> = emptyList(),

//  @field:NotEmpty
  val username: String

) {
  object ModelMapper {

    fun toPerson(from: PersonCreateInput) = Person(
      ObjectId.get().toHexString(),
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
