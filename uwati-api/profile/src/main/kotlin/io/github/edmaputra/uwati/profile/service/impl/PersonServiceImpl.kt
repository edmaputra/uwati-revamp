package io.github.edmaputra.uwati.profile.service.impl

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enum.PersonType
import io.github.edmaputra.uwati.profile.service.PersonService
import java.util.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PersonServiceImpl : PersonService {

    override fun listPersons(): Flux<Person> {
        return Flux.just(
            Person(
                UUID.randomUUID().toString(), "ABC", "Bangun", "bangun@gmail.co", PersonType.SUPERUSER,
                Collections.singletonMap("street", "abc"), "0121", Collections.singletonMap("street", "abc")
            )
        )
    }
}