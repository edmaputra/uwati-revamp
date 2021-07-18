package io.github.edmaputra.uwati.profile.resolver

//@Component
//class PersonQuery(
//  private val service: PersonService
//) : Query {
//
//  fun persons(): Flux<Person> = service.findAll()
//
//  fun person(id: String): Mono<Person> = service.getById(id)
//
//}
//  fun personsPageable(page: Int, size: Int): Page<Person> = service.findAll(PageRequest.of(page, size));
//}

//val configWithReactorTypeMonad = SchemaGeneratorConfig(
//  supportedPackages = listOf("io.github.edmaputra.uwati.profile"),
//  hooks = ReactorTypeMonadHooks(),
//  dataFetcherFactoryProvider = ReactorTypeDataFetcherFactoryProvider(objectMapper = ObjectMapper())
//)
//
//toSchema(queries = listOf(TopLevelObject(PersonQuery())), config = configWithReactorTypeMonad)
