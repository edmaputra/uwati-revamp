package io.github.edmaputra.uwati.profile.repository

import io.github.edmaputra.uwati.profile.entity.Person
import io.github.edmaputra.uwati.profile.enum.PersonType
import java.util.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.test.StepVerifier

@DataMongoTest
class PersonRepositoryTest @Autowired constructor(
    private val repository: PersonRepository
) {

    @Test
    fun `when findAll then return all records`() {
        val person = Person(
            UUID.randomUUID().toString(), "ABC", "Bangun", "bangun@gmail.co", PersonType.SUPERUSER,
            Collections.singletonMap("street", "abc"), "0121"
        )

        val result = repository.deleteAll()
            .thenMany(repository.save(person))
            .thenMany(repository.findAll())


        StepVerifier.create(result).expectNextCount(1).verifyComplete()

        result.subscribe { i -> println(i) }
    }

}