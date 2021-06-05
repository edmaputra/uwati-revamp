package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enum.PersonType
import io.github.edmaputra.uwati.profile.repository.PersonRepository
import io.github.edmaputra.uwati.profile.service.PersonService
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import kotlin.collections.HashMap

@Service
class PersonServiceImpl(
  private val repository: PersonRepository
) : PersonService {

  override fun getAll(): Flux<Person> {
    return Flux.just(
      Person(
        UUID.randomUUID().toString(),
        "ABC",
        "Bangun",
        "bangun@gmail.co",
        PersonType.SUPERUSER,
        Collections.singletonMap("street", "abc"),
        "0121",
        Collections.singletonMap("street", "abc")
      )
    )
  }

  override fun getById(request: Mono<String>): Mono<Person> {
    return repository.findById(request);
  }

  override fun create(request: Mono<PersonCreateRequest>): Mono<Person> {
    return request.flatMap { request ->
      Mono.just(
        repository.save(
          Person(
            UUID.randomUUID().toString(),
            "P-" + request.type + "-001",
            request.name,
            request.email,
            request.type,
            request.address,
            request.phone,
            request.metadata
          )
        )
      )
    }
      .map { entity -> entity }
//    return request.map { r ->
//      Person(
//        UUID.randomUUID().toString(),
//        "P-" + r.type + "-001",
//        r.name,
//        r.email,
//        r.type,
//        r.address,
//        r.phone,
//        r.metadata
//      )
//    }
  }


  override fun update(request: Mono<PersonUpdateRequest>): Mono<Person> {
    return request.map { r ->
      Person(
        UUID.randomUUID().toString(),
        r.id,
        r.name,
        r.email,
        r.type,
        r.address,
        r.phone,
        r.metadata
      )
    }
  }

  override fun delete(request: Mono<String>): Mono<Person> {
    return request.map { r ->
      Person(
        UUID.randomUUID().toString(),
        r,
        "deleted",
        "delete@mail.id",
        PersonType.ADMINISTRATOR,
        HashMap(),
        "123123",
        HashMap()
      )
    }
  }
}
