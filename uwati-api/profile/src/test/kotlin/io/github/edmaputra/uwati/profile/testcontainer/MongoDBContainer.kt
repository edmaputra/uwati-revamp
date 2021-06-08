package io.github.edmaputra.uwati.profile.testcontainer

import org.testcontainers.containers.GenericContainer

class MongoDBContainer : GenericContainer<MongoDBContainer> {

  constructor() : super("mongo:3.2.4")

  constructor(dockerImageName: String) : super("mongo:3.2.4") {
    addExposedPort(27017)
  }
}
