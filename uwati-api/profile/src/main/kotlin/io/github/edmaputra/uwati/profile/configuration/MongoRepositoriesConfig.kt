package io.github.edmaputra.uwati.profile.configuration

import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


//@Configuration
@EnableReactiveMongoRepositories()
class MongoRepositoriesConfig : AbstractReactiveMongoConfiguration() {

  override fun getDatabaseName(): String = "profiles"


  override fun reactiveMongoClient(): MongoClient {
    return MongoClients.create(
      "mongodb://profilesAdmin:kotabangun1@localhost:27017/admin"
    )
  }

}
