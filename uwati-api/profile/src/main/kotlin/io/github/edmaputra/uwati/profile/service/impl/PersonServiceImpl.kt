package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.error.NotFoundException
import io.github.edmaputra.uwati.profile.repository.PersonRepository
import io.github.edmaputra.uwati.profile.service.PersonService
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
  private val repository: PersonRepository
) : PersonService {

  override fun findAll(): List<Person> = repository.findAll()

  override fun getById(request: ObjectId): Person =
    repository.findById(request).orElseThrow { NotFoundException("Not Found") }

  override fun create(request: PersonCreateRequest): Person =
    repository.save(
      PersonCreateRequest.ModelMapper.toPerson(request)
    )

  override fun update(request: PersonUpdateRequest): Person =
    repository.findById(request.id)
      .map{ s ->
        s.name = request.name
        s.email = request.name
        s.address = request.address
        s.type = request.type
        s.phone = request.phone
        s.metadata = request.metadata
        repository.save(s)
        s
      }
      .orElseThrow { NotFoundException("Not Found") }


  override fun delete(request: ObjectId) = repository.deleteById(request)
}
