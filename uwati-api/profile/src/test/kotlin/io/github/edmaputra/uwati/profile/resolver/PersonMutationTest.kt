package io.github.edmaputra.uwati.profile.resolver

import com.fasterxml.jackson.databind.JsonNode
import io.github.edmaputra.uwati.profile.util.GraphQLTestUtility
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
internal class PersonMutationTest {

  @field:Autowired
  private lateinit var restTemplate: TestRestTemplate

  @field:Autowired
  private lateinit var graphQLTestUtility: GraphQLTestUtility

  @field:Autowired
  private lateinit var createAdministratorPayload: String

  @Test
  fun `create thing should succeed when the input is valid`() {
    val payload = graphQLTestUtility.createJsonQuery(createAdministratorPayload)

    val headers = HttpHeaders()
    headers.contentType = MediaType.APPLICATION_JSON

    val response: ResponseEntity<String> = restTemplate.exchange(
      GraphQLTestUtility.ENDPOINT_LOCATION,
      HttpMethod.POST, HttpEntity(payload, headers),
      String::class.java
    )

    assertEquals(HttpStatus.OK, response.statusCode)

    val parsedResponse: JsonNode = graphQLTestUtility.parse(response.body)
    assertThat(parsedResponse.get("data")).isNotNull
    assertThat(parsedResponse.get("data").get("create").get("id")).isNotNull
    assertThat(parsedResponse.get("data").get("create").get("name").textValue()).isEqualTo("Bangun")
    assertThat(parsedResponse.get("data").get("create").get("personId").textValue()).isEqualTo("ADM-00001")
    println(parsedResponse.get("data").get("create"))
  }
}
