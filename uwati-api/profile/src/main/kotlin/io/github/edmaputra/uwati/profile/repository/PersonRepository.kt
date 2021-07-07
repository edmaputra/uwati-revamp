package io.github.edmaputra.uwati.profile.repository

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enumeration.PersonType
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : MongoRepository<Person, ObjectId> {

  fun findByDeletedFlagIsFalse(): List<Person>

  fun findByDeletedFlagIsFalse(pageable: Pageable): Page<Person>

//  fun findByDeletedFlagIsFalse(pageable: Pageable): Page<Person>

  fun findFirstByTypeOrderByCreatedDateTimeDesc(type: PersonType): Optional<Person>

}
