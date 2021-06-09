package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.error.NotFoundException
import io.github.edmaputra.uwati.profile.repository.PersonRepository
import io.github.edmaputra.uwati.profile.service.PersonService
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PersonServiceImpl(
  private val repository: PersonRepository
) : PersonService {

  override fun findAll(): List<Person> = repository.findAll()

  override fun getById(request: ObjectId): Person =
    repository.findById(request).orElseThrow { NotFoundException("Not Found") }

  override fun create(request: PersonCreateRequest): Person {
    val person = PersonCreateRequest.ModelMapper.toPerson(request)
    person.personId = generatePersonId(request)
    return repository.save(person)
  }

  override fun update(request: PersonUpdateRequest): Person =
    repository.findById(request.id)
      .map{ s ->
        s.name = request.name
        s.email = request.email
        s.address = request.address
        s.phone = request.phone
        s.metadata = request.metadata
        s.modifiedDateTime = ZonedDateTime.now().toEpochSecond()
        repository.save(s)
      }
      .orElseThrow { NotFoundException("Not Found") }


  override fun delete(request: ObjectId) = repository.deleteById(request)

  private fun generatePersonId(request: PersonCreateRequest): String {
    val savedPerson = repository.findFirstByTypeOrderByCreatedDateTimeDesc(request.type)
    return if (savedPerson.isPresent) {
      val sequenceNumber = savedPerson.get().personId.substring(savedPerson.get().personId.length - 5)
      request.type.v + "-" + String.format("%05d", (Integer.valueOf(sequenceNumber) + 1))
    } else {
      request.type.v + "-00001"
    }
  }
}
