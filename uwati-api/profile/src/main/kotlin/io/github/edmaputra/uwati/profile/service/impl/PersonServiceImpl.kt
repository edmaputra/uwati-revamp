package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.exception.NotFoundException
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

  override fun findAll(): List<Person> = repository.findByDeletedFlagIsFalse()

  override fun getById(request: ObjectId): Person =
    repository.findById(request).orElseThrow { NotFoundException("Not Found") }

  override fun create(request: PersonCreateRequest): Person {
    val person = PersonCreateRequest.ModelMapper.toPerson(request)
    person.personId = generatePersonId(request)
    return repository.save(person)
  }

  override fun update(request: PersonUpdateRequest): Person =
    repository.findById(request.id)
      .map { saved ->
        updateValue(request, saved)
        saved.modifiedDateTime = ZonedDateTime.now().toEpochSecond()
        repository.save(saved)
      }
      .orElseThrow { NotFoundException("Record with id ${request.id} cannot be found") }


  override fun delete(id: ObjectId) {
    val personToBeDeleted = getById(id)
    personToBeDeleted.deletedDateTime = ZonedDateTime.now().toEpochSecond()
    personToBeDeleted.deletedFlag = true
    repository.save(personToBeDeleted)
  }

  override fun hardDelete(id: ObjectId) = repository.deleteById(id)

  private fun updateValue(source: PersonUpdateRequest, target: Person) {
    target.name = source.name
    target.email = source.email
    target.address = source.address
    target.phone = source.phone
    target.metadata = source.metadata
  }

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
