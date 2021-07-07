package io.github.edmaputra.uwati.profile.input

import io.github.edmaputra.uwati.profile.common.MapEntry
import org.bson.types.ObjectId
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PersonUpdateInput(

  @field:NotNull
  val id: ObjectId,

  @field:NotEmpty
  val name: String,

  @field:NotEmpty
  val email: String,

  val phone: String,

  val address: List<MapEntry>,

  val metadata: List<MapEntry> = emptyList()

)
