plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version = "3.2.3"

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:2.3.3")
    implementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("io.ktor:ktor-server-config-yaml:2.3.3")
    implementation("io.ktor:ktor-server-auth-jvm:2.3.3")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.3.3")
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.41.1")

    implementation("io.ktor:ktor-server-swagger:2.3.4")
    implementation("io.ktor:ktor-server-openapi:2.3.4")
    implementation("io.ktor:ktor-server-websockets:2.3.4")
    implementation("io.ktor:ktor-server-call-logging:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.3")
    implementation("io.ktor:ktor-server-netty:2.3.3")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.0")
    testImplementation("io.mockk:mockk:1.13.5")
    implementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}