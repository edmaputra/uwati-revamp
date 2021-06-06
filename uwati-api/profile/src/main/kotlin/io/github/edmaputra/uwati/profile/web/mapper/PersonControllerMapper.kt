package io.github.edmaputra.uwati.profile.web.mapper

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest

interface PersonControllerMapper {

  fun toPerson(request: PersonCreateRequest): Person

  fun toPerson(request: PersonUpdateRequest): Person
}
