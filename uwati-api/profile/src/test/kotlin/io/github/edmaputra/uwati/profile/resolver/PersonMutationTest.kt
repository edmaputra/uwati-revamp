package io.github.edmaputra.uwati.profile.resolver

import com.fasterxml.jackson.databind.JsonNode
import io.github.edmaputra.uwati.profile.testcontainer.MongoDBContainer
import io.github.edmaputra.uwati.profile.util.GraphQLTestUtility
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.*
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = MongoDBContainer.m)

internal class PersonMutationTest {

  @field:Autowired
  private lateinit var restTemplate: TestRestTemplate

  @field:Autowired
  private lateinit var graphQLTestUtility: GraphQLTestUtility

  @field:Autowired
  private lateinit var createAdministratorPayload: String

  private val mongoDbContainer: MongoDBContainer? = null


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
    assertNotNull(parsedResponse.get("data"))
    assertNotNull(parsedResponse.get("data").get("id"))
    assertEquals("Bangun", parsedResponse.get("data").get("name"))
  }

  class MongoDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext?> {
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext?) {
      val values = TestPropertyValues.of(
        "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
        "spring.data.mongodb.port=" + mongoDbContainer.getPort()
      )
      values.applyTo(configurableApplicationContext)
    }
  }
}
