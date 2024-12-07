import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED

plugins {
  kotlin("jvm") version "2.1.0"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.github.shiguruikai:combinatoricskt:1.6.0")
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events(PASSED, FAILED)
    exceptionFormat = FULL
    showExceptions = true
    showStackTraces = true
  }
}

kotlin {
  jvmToolchain(21)
}
