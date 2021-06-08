package io.github.edmaputra.uwati.profile

import graphql.Scalars
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ProfileApplication

fun main(args: Array<String>) {
  runApplication<ProfileApplication>(*args)
}
