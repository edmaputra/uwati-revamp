package io.github.edmaputra.uwati.profile.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.input.PersonCreateInput
import io.github.edmaputra.uwati.profile.input.PersonDeleteInput
import io.github.edmaputra.uwati.profile.input.PersonUpdateInput
import io.github.edmaputra.uwati.profile.service.PersonService
import org.bson.types.ObjectId
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Validated
@Component
class PersonMutationResolver(
  private val service: PersonService
) : GraphQLMutationResolver {

  fun create(@Valid createInput: PersonCreateInput): Person = service.create(createInput)

  fun update(@Valid updateInput: PersonUpdateInput): Person = service.update(updateInput)

  fun delete(@Valid deleteInput: PersonDeleteInput): ObjectId {
    if (deleteInput.hardDelete) {
      service.hardDelete(deleteInput.id)
    } else {
      service.delete(deleteInput.id)
    }
    return deleteInput.id
  }


}
