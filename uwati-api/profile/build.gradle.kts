plugins {
  id("org.springframework.boot")
  kotlin("kapt")
}

extra["springCloudVersion"] = "2020.0.3"

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.security:spring-security-rsa")

  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.experimental:graphql-spring-boot-starter:1.0.0-SNAPSHOT")

//  implementation("com.expediagroup:graphql-kotlin-spring-server:5.0.0-alpha.0")

  implementation("com.querydsl:querydsl-core")
  implementation("com.querydsl:querydsl-apt")
  implementation("com.querydsl:querydsl-mongodb")
  kapt("org.springframework.boot:spring-boot-configuration-processor")
  kapt("com.querydsl:querydsl-apt")

  testRuntimeOnly("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}

kapt {
  annotationProcessor("org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor")
}

java.sourceSets.create("generated").java {
  srcDir("src/generated/kotlin")
}

configure<org.springframework.boot.gradle.dsl.SpringBootExtension> {
  buildInfo()
}
