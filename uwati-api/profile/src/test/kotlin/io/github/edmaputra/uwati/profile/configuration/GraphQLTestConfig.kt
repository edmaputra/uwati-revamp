package io.github.edmaputra.uwati.profile.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

@Configuration
class GraphQLTestConfig {

  @Value("classpath:graphql/query_wrapper.json")
  private lateinit var queryWrapperFile: Resource

  @Value("classpath:graphql/test-create-administrator.graphql")
  private lateinit var createAdministratorPayload: Resource

  @Bean
  fun createAdministratorPayload(): String = StreamUtils.copyToString(createAdministratorPayload.inputStream, StandardCharsets.UTF_8)

  @Bean
  fun queryWrapper(): String = StreamUtils.copyToString(queryWrapperFile.inputStream, StandardCharsets.UTF_8)

}
