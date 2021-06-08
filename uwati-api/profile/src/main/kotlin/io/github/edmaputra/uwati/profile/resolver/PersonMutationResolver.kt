package io.github.edmaputra.uwati.profile.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import io.github.edmaputra.uwati.profile.service.PersonService
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import org.springframework.stereotype.Component
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class PersonMutationResolver(
  private val service: PersonService
) : GraphQLMutationResolver {

  val personType = mapOf(
    "Administrator" to PersonType.ADMINISTRATOR,
    "Cashier" to PersonType.CASHIER,
    "Doctor" to PersonType.DOCTOR,
    "SuperUser" to PersonType.SUPERUSER,
    "Pharmacist" to PersonType.PHARMACIST
  )

  fun create(name: String, email: String, type: String, phone: String, address: Map<String, Any>): Person =
    service.create(PersonCreateRequest(name, email, personType[type], address, phone, emptyMap()))

}
