package io.github.edmaputra.uwati.profile.wiring

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.schema.idl.RuntimeWiring
import io.github.edmaputra.uwati.profile.service.PersonService
import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer
import org.springframework.stereotype.Component

@Component
class PersonWiring(
  private val personService: PersonService,
  private val objectMapper: ObjectMapper
) : RuntimeWiringBuilderCustomizer {

  override fun customize(builder: RuntimeWiring.Builder) {
    builder.type("Query") { wiring -> wiring.dataFetcher("person") { env -> this.personService.getById(env.getArgument("id")) } }
    builder.type("Query") { wiring ->
      wiring.dataFetcher("persons") { env ->
        this.personService.findAll(
          env.getArgument("page"),
          env.getArgument("size"),
          env.getArgument("sort"),
          env.getArgument("asc"),
          env.getArgument("search")
        )
      }
    }

//    builder.type("Mutation") { wiring ->
//      wiring.dataFetcher("create") { env ->
//        {
//          val temp = env.getArgument<Any>("input")
//          val p = objectMapper.convertValue(temp, PersonCreateInput::class.java)
//          this.personService.create(p)
//          true
//        }
//      }
//    }

//    builder.type("Mutation") { wiring ->
//      wiring.dataFetcher("request") { env ->
//        {
//          if (env.getArgument("hardDelete")) {
//            personService.hardDelete(env.getArgument("id"))
//          } else {
//            personService.delete(env.getArgument("id"));
//          }
//          env.getArgument<String>("id")
//        }
//      }
//    }
  }
}
