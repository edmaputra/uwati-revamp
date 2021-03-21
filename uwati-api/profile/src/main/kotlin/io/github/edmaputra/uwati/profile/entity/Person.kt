package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.enum.PersonType
import org.springframework.data.annotation.Id

data class Person(
  @Id
  val id: String,

  val personId: String,

  val name: String,

  val email: String,

  val type: PersonType,

  val address: Map<String, Any>,

  val phone: String,

  val metadata: Map<String, Any>
)
