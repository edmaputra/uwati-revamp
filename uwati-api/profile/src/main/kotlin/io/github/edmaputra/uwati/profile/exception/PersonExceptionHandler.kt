package io.github.edmaputra.uwati.profile.exception

import graphql.kickstart.spring.error.ThrowableGraphQLError
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
class PersonExceptionHandler {

  @ExceptionHandler(NotFoundException::class)
  fun handle(e: NotFoundException) = ThrowableGraphQLError(e)

  @ExceptionHandler(RuntimeException::class)
  fun handle(e: RuntimeException) = ThrowableGraphQLError(e, "Internal Server Error")

}
