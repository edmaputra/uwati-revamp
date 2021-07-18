package io.github.edmaputra.uwati.profile.configuration

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

@Configuration
class MongoTemplateConfig(
  private val mongoClient: MongoClient
) {

  @Bean
  fun reactiveMongoTemplate(): ReactiveMongoTemplate =
    ReactiveMongoTemplate(mongoClient, "profiles")
}
