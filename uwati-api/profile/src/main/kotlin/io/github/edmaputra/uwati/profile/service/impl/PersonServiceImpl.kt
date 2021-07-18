package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.input.PersonCreateInput
import io.github.edmaputra.uwati.profile.repository.PersonRepository
import io.github.edmaputra.uwati.profile.service.PersonService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonServiceImpl(
  private val repository: PersonRepository,
  private val template: ReactiveMongoTemplate
) : PersonService {

  override fun findAll(): Flux<Person> = repository.findAll()

  override fun findAll(page: Int, size: Int, sort: String, ascending: Boolean, search: String?): Flux<Person> =
    template.find(createQuery(page, size, sort, ascending, search), Person::class.java)

  override fun getById(id: String): Mono<Person> = repository.findById(id)

  override fun create(input: PersonCreateInput) {
    val person = PersonCreateInput.ModelMapper.toPerson(input)
    repository.save(person);
  }

  fun createQuery(page: Int = 0, size: Int = 20, sort: String = "id", ascending: Boolean, search: String?): Query {
    val query =
      Query().with(PageRequest.of(page, size, if (ascending) Sort.Direction.ASC else Sort.Direction.DESC, sort))

    if (search != null && search.isNotEmpty()) {
      query.addCriteria(
        Criteria.where("name").regex(".*$search.*", "i")
      )
    }

    return query
  }


  //
//  override fun update(input: PersonUpdateInput): Person =
//    repository.findById(input.id)
//      .map { saved ->
//        updateValue(input, saved)
//        saved.modifiedDateTime = ZonedDateTime.now().toEpochSecond()
//        repository.save(saved)
//      }
//      .orElseThrow { NotFoundException("Record with id ${input.id} cannot be found") }
//
//
  override fun delete(id: String) {
//    val personToBeDeleted = getById(id)
//    personToBeDeleted. = ZonedDateTime.now().toEpochSecond()
//    personToBeDeleted.deletedFlag = true

    getById(id).map { repository.save(it) }
  }

  override fun hardDelete(id: String) {
    repository.deleteById(id)
  }
//
//  private fun updateValue(source: PersonUpdateInput, target: Person) {
//    target.name = source.name
//    target.email = source.email
//    target.address = source.address
//    target.phone = source.phone
//    target.metadata = source.metadata
//  }
//
//  private fun generatePersonId(person: Person): String {
//    val savedPerson = repository.findFirstByTypeOrderByCreatedDateTimeDesc(person.type)
//    return if (savedPerson.isPresent) {
//      val sequenceNumber = savedPerson.get().personId.substring(savedPerson.get().personId.length - 5)
//      person.type.v + "-" + String.format("%05d", (Integer.valueOf(sequenceNumber) + 1))
//    } else {
//      person.type.v + "-00001"
//    }
//  }
}
