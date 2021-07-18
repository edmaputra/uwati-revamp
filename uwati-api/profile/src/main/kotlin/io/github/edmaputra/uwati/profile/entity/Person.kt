package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.common.Auditable
import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document
open class Person(

  @Id
  var id: String,

  @Indexed(unique = true)
  var personId: String,

  var name: String,

  @Indexed(unique = true)
  var email: String,

  var phone: String,

  var type: PersonType = PersonType.CASHIER,

  var address: List<MapEntry>,

  var metadata: List<MapEntry> = emptyList(),

  @Indexed(unique = true)
  val username: String

) : Auditable()
