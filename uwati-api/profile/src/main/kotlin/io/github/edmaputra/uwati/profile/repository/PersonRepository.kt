package io.github.edmaputra.uwati.profile.repository

import io.github.edmaputra.uwati.profile.entity.Person
import org.springframework.data.repository.reactive.ReactiveSortingRepository

interface PersonRepository : ReactiveSortingRepository<Person, String> {

}