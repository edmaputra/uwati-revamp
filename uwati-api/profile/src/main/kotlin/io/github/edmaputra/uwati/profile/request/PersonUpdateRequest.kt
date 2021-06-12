package io.github.edmaputra.uwati.profile.request

import io.github.edmaputra.uwati.profile.common.MapEntry
import org.bson.types.ObjectId

data class PersonUpdateRequest(

  val id: ObjectId,

  val name: String = "",

  val email: String = "",

  val address: List<MapEntry>,

  val phone: String = "",

  val metadata: List<MapEntry> = emptyList()

)
