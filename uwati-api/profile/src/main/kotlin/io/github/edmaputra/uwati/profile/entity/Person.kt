package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.enum.PersonType
import org.springframework.data.annotation.*
import java.time.ZonedDateTime

data class Person(
  @Id
  val id: String,

  val personId: String,

  val name: String,

  val email: String,

  val type: PersonType,

  val address: Map<String, Any>,

  val phone: String,

  val metadata: Map<String, Any>,

  @CreatedBy
  val createdBy: String = "",

  @CreatedDate
  val createdDateTime: Long = 0,

  @LastModifiedBy
  val modifiedBy: String = "",

  @LastModifiedDate
  val modifiedDateTime: Long = 0,

  val deletedBy: String = "",

  val deletedDateTime: Long = 0,

  val deletedFlag: Boolean = false
)
