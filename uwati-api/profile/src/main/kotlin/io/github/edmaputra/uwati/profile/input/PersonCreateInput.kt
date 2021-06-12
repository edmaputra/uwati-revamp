package io.github.edmaputra.uwati.profile.input

import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import javax.validation.constraints.NotEmpty

data class PersonCreateInput(

  @field:NotEmpty
  val name: String,

  @field:NotEmpty
  val email: String,

  val phone: String,

  @field:NotEmpty
  val type: String,

  val address: List<MapEntry> = emptyList(),

  val metadata: List<MapEntry> = emptyList(),

  @field:NotEmpty
  val username: String

) {
  object ModelMapper {

    private val PERSON_TYPE = mapOf(
      "Administrator" to PersonType.ADMINISTRATOR,
      "Cashier" to PersonType.CASHIER,
      "Doctor" to PersonType.DOCTOR,
      "SuperUser" to PersonType.SUPERUSER,
      "Pharmacist" to PersonType.PHARMACIST
    )

    fun toPerson(from: PersonCreateInput) = Person(
      ObjectId.get(),
      "",
      from.name,
      from.email,
      from.phone,
      PERSON_TYPE[from.type]!!,
      from.address,
      from.metadata,
      from.username
    )
  }
}
