package io.github.edmaputra.uwati.profile.execution

//class ReactorTypeFunctionDataFetcher(target: Any?, fn: KFunction<*>, objectMapper: ObjectMapper) :
//  FunctionDataFetcher(target, fn, objectMapper) {
//  override fun get(environment: DataFetchingEnvironment): Any? =
//    when (val result = super.get(environment)) {
//      is Mono<*> -> result.toFuture()
//      is Flux<*> -> result.collectList().block()
//      else -> result
//    }
//}
