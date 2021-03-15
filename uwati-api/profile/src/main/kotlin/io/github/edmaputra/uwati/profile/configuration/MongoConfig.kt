package io.github.edmaputra.uwati.profile.configuration

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import java.util.*
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName() = "profile"

    override fun reactiveMongoClient(): MongoClient = MongoClients.create()

    override fun getMappingBasePackages(): MutableList<String> =
        Collections.singletonList("io.github.edmaputra.uwati.profile")

}