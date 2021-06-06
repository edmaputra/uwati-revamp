package io.github.edmaputra.uwati.profile.repository

import io.github.edmaputra.uwati.profile.entity.Person
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : MongoRepository<Person, ObjectId> {
}
