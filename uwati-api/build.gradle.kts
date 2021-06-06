import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  idea
  kotlin("jvm") version "1.4.32" apply false
  kotlin("plugin.spring") version "1.4.32" apply false
  id("org.springframework.boot") version "2.4.6" apply false
  id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

allprojects {
  group = "io.github.edmaputra.uwati"
  version = "0.0.2-SNAPSHOT"

  repositories {
    mavenCentral()
  }

  tasks.withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "11"
    }
  }
}

subprojects {

  apply(plugin = "idea")
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")
  apply(plugin = "io.spring.dependency-management")

  dependencies {
    "implementation"("org.jetbrains.kotlin:kotlin-reflect")
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    "implementation"("io.projectreactor.kotlin:reactor-kotlin-extensions")
    "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")

    "implementation"("com.graphql-java-kickstart:graphql-spring-boot-starter:11.0.0")
    "implementation"("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.0.0")
    "compile"("com.graphql-java-kickstart:graphql-java-tools:11.0.1")

    "testCompile"("com.graphql-java-kickstart:graphql-spring-boot-starter-test:11.0.0")

  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

}
