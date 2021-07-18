package io.github.edmaputra.uwati.profile.resolver

//@Validated
//@Component
//class PersonMutation(
//  private val service: PersonService
//) : Mutation {
//
//  fun create(
//    name: String,
//    type: PersonType,
//    email: String,
//    username: String
//  ): CompletableFuture<Person> {
//    return service.create(PersonCreateInput(name, email, "", type, emptyList(), emptyList(), username)).toFuture()
//  }
//
//  fun update(@Valid updateInput: PersonUpdateInput): Person = service.update(updateInput)
//
//  fun delete(@Valid deleteInput: PersonDeleteInput): ObjectId {
//    if (deleteInput.hardDelete) {
//      service.hardDelete(deleteInput.id)
//    } else {
//      service.delete(deleteInput.id)
//    }
//    return deleteInput.id
//  }


//}
