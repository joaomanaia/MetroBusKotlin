plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "me.joaomanaia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Google truth
    testImplementation("com.google.truth:truth:1.1.3")

    // Mockk
    testImplementation("io.mockk:mockk:1.12.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}