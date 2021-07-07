package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.exception.NotFoundException
import io.github.edmaputra.uwati.profile.input.PersonCreateInput
import io.github.edmaputra.uwati.profile.input.PersonUpdateInput
import io.github.edmaputra.uwati.profile.repository.PersonRepository
import io.github.edmaputra.uwati.profile.service.PersonService
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PersonServiceImpl(
  private val repository: PersonRepository
) : PersonService {

  override fun findAll(): List<Person> = repository.findByDeletedFlagIsFalse()

  override fun findAll(pageable: Pageable): Page<Person> = repository.findByDeletedFlagIsFalse(pageable)

  override fun getById(id: ObjectId): Person =
    repository.findById(id).orElseThrow { NotFoundException("Not Found") }

  override fun create(input: PersonCreateInput): Person {
    val person = PersonCreateInput.ModelMapper.toPerson(input)
    person.personId = generatePersonId(person)
    return repository.save(person)
  }

  override fun update(input: PersonUpdateInput): Person =
    repository.findById(input.id)
      .map { saved ->
        updateValue(input, saved)
        saved.modifiedDateTime = ZonedDateTime.now().toEpochSecond()
        repository.save(saved)
      }
      .orElseThrow { NotFoundException("Record with id ${input.id} cannot be found") }


  override fun delete(id: ObjectId) {
    val personToBeDeleted = getById(id)
    personToBeDeleted.deletedDateTime = ZonedDateTime.now().toEpochSecond()
    personToBeDeleted.deletedFlag = true
    repository.save(personToBeDeleted)
  }

  override fun hardDelete(id: ObjectId) = repository.deleteById(id)

  private fun updateValue(source: PersonUpdateInput, target: Person) {
    target.name = source.name
    target.email = source.email
    target.address = source.address
    target.phone = source.phone
    target.metadata = source.metadata
  }

  private fun generatePersonId(person: Person): String {
    val savedPerson = repository.findFirstByTypeOrderByCreatedDateTimeDesc(person.type)
    return if (savedPerson.isPresent) {
      val sequenceNumber = savedPerson.get().personId.substring(savedPerson.get().personId.length - 5)
      person.type.v + "-" + String.format("%05d", (Integer.valueOf(sequenceNumber) + 1))
    } else {
      person.type.v + "-00001"
    }
  }
}
