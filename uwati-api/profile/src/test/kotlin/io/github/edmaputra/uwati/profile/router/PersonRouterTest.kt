package io.github.edmaputra.uwati.profile.router

import io.github.edmaputra.uwati.profile.enum.PersonType
import io.github.edmaputra.uwati.profile.web.request.PersonCreateRequest
import io.github.edmaputra.uwati.profile.web.request.PersonUpdateRequest
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PersonRouterTest(@Autowired val webTestClient: WebTestClient) {

  @Test
  fun `given request, when getAll, then return expected values`() {
    webTestClient.get().uri("/persons")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$").isArray
      .jsonPath("$[*].id").isNotEmpty
      .jsonPath("$[*].name", containsInAnyOrder("Bangun"))
  }

  @Test
  fun `given id, when getById, then return expected values`() {
    webTestClient.get().uri("/persons/P-SUP-001")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isNotEmpty
      .jsonPath("$.personId").isEqualTo("P-SUP-001")
      .jsonPath("$.name", containsInAnyOrder("Bangun"))
  }

  @Test
  fun `given create request, when create, then return created values`() {
    webTestClient.post().uri("/persons")
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        Mono.just(
          PersonCreateRequest(
            "Test",
            "bangun@gmail.co",
            PersonType.SUPERUSER,
            Collections.singletonMap("street", "abc"),
            "0121",
            Collections.singletonMap("data", "1")
          )
        ), PersonCreateRequest::class.java
      )
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.id").isNotEmpty
      .jsonPath("$.name").isEqualTo("Test")
      .jsonPath("$.email").isEqualTo("bangun@gmail.co")
  }

  @Test
  fun `given update request, when update, then return updated values`() {
    webTestClient.put().uri("/persons")
      .contentType(MediaType.APPLICATION_JSON)
      .body(
        Mono.just(
          PersonUpdateRequest(
            "P-SUP-001",
            "TestUpdate",
            "bangun.update@gmail.co",
            PersonType.SUPERUSER,
            Collections.singletonMap("street", "abc"),
            "1111",
            Collections.singletonMap("data", "1")
          )
        ), PersonCreateRequest::class.java
      )
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isNotEmpty
      .jsonPath("$.personId").isEqualTo("P-SUP-001")
      .jsonPath("$.name").isEqualTo("TestUpdate")
      .jsonPath("$.email").isEqualTo("bangun.update@gmail.co")
      .jsonPath("$.phone").isEqualTo("1111")
  }

  @Test
  fun `given delete request, when delete, then return updated values`() {
    webTestClient.delete().uri("/persons/P-SUP-001")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isNotEmpty
      .jsonPath("$.personId").isEqualTo("P-SUP-001")
      .jsonPath("$.name").isEqualTo("deleted")
      .jsonPath("$.email").isEqualTo("delete@mail.id")
      .jsonPath("$.phone").isEqualTo("123123")
  }
}
