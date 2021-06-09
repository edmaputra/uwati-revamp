package io.github.edmaputra.uwati.profile.web.request

import org.bson.types.ObjectId

data class PersonUpdateRequest(

  val id: ObjectId,

  val name: String = "",

  val email: String = "",

  val address: Map<String, Any> = emptyMap(),

  val phone: String = "",

  val metadata: Map<String, Any> = emptyMap()

)
