package io.github.edmaputra.uwati.profile.web.request

import io.github.edmaputra.uwati.profile.enum.PersonType

data class PersonUpdateRequest(

  val id: String,

  val name: String = "",

  val email: String = "",

  val type: PersonType = PersonType.ADMINISTRATOR,

  val address: Map<String, Any> = emptyMap(),

  val phone: String = "",

  val metadata: Map<String, Any> = emptyMap()

)
