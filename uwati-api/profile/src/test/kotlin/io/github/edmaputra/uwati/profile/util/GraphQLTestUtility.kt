package io.github.edmaputra.uwati.profile.util

import com.fasterxml.jackson.core.io.JsonStringEncoder
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GraphQLTestUtility {

  @field:Autowired
  private lateinit var queryWrapper: String

  private val jsonStringEncoder = JsonStringEncoder.getInstance()

  fun createJsonQuery(graphQL: String): String = queryWrapper.replace("__payload__", escapeQuery(graphQL))

  private fun escapeQuery(graphQL: String): String = jsonStringEncoder.quoteAsString(graphQL).joinToString("")

  companion object {
    const val ENDPOINT_LOCATION = "/graphql"
  }

  fun parse(payload: String?): JsonNode = ObjectMapper().readTree(payload)
}
