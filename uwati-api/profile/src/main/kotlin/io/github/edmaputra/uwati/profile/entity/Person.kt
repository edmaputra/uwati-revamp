package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.common.MapEntry
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.index.Indexed
import java.time.ZonedDateTime

data class Person(
  @Id
  var id: ObjectId,

  @Indexed(unique = true)
  var personId: String,

  var name: String,

  @Indexed(unique = true)
  var email: String,

  var phone: String,

  var type: PersonType = PersonType.CASHIER,

  var address: List<MapEntry>,

  var metadata: List<MapEntry> = emptyList(),

  @CreatedBy
  var createdBy: String = "",

  @CreatedDate
  var createdDateTime: Long = ZonedDateTime.now().toEpochSecond(),

  @LastModifiedBy
  var modifiedBy: String = "",

  @LastModifiedDate
  var modifiedDateTime: Long = ZonedDateTime.now().toEpochSecond(),

  var deletedBy: String = "",

  var deletedDateTime: Long = 0,

  var deletedFlag: Boolean = false
)
