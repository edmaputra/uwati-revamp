package io.github.edmaputra.uwati.profile.common

import java.time.ZonedDateTime

open class Auditable {

  open var createdBy: String = ""

  open var createdDateTime: ZonedDateTime = ZonedDateTime.now()

  open var modifiedBy: String = ""

  open var modifiedDateTime: ZonedDateTime = ZonedDateTime.now()

  open var deleted: Boolean = false

  open var deletedBy: String? = ""

  open var deletedDateTime: ZonedDateTime? = null
}
