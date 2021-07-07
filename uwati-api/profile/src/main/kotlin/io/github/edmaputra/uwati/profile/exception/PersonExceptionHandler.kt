package io.github.edmaputra.uwati.profile.exception

import graphql.kickstart.spring.error.ThrowableGraphQLError
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

@Component
class PersonExceptionHandler {

  @ExceptionHandler(NotFoundException::class, ConstraintViolationException::class)
  fun handle(e: Exception) = ThrowableGraphQLError(e)

  @ExceptionHandler(RuntimeException::class)
  fun handle(e: RuntimeException) = ThrowableGraphQLError(e, "Internal Server Error")

}
