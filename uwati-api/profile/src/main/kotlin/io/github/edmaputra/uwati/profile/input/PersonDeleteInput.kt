package io.github.edmaputra.uwati.profile.input

import org.bson.types.ObjectId
import javax.validation.constraints.NotNull

data class PersonDeleteInput(

  @field:NotNull
  val id: ObjectId,

  val hardDelete: Boolean

)
