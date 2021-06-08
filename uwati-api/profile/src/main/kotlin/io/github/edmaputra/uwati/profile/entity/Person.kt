package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import org.springframework.data.annotation.*

data class Person(
  @Id
  var id: ObjectId,

  var personId: String,

  var name: String,

  var email: String,

  var type: PersonType? = PersonType.CASHIER,

  var address: Map<String, Any>,

  var phone: String,

  var metadata: Map<String, Any>,

  @CreatedBy
  var createdBy: String = "",

  @CreatedDate
  var createdDateTime: Long = 0,

  @LastModifiedBy
  var modifiedBy: String = "",

  @LastModifiedDate
  var modifiedDateTime: Long = 0,

  var deletedBy: String = "",

  var deletedDateTime: Long = 0,

  var deletedFlag: Boolean = false
)
