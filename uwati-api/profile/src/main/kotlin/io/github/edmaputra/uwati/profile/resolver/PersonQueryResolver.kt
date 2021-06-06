package io.github.edmaputra.uwati.profile.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.service.PersonService
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Component

@Component
class PersonQueryResolver(
  private val service: PersonService,
  private val mongoOperation: MongoOperations
) : GraphQLQueryResolver {

  fun persons(): List<Person> = service.findAll();
}
