import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
    kotlin("jvm") version "2.1.20"
}

repositories {
    mavenCentral()
}

group = "no.nav.helse.flex"
version = properties["version"] ?: "local-build"
description = "sykepengesoknad-kafka"
java.sourceCompatibility = JavaVersion.VERSION_21

val kluentVersion = "1.73"
val junitVersion = "5.13.4"

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Forhindrer bruk av Gradles innebygde launcher med annen versjon.
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/sykepengesoknad-kafka")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {

            pom {
                name.set("sykepengesoknad-kafka")
                url.set("https://github.com/navikt/sykepengesoknad-kafka")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/navikt/sykepengesoknad-kafka.git")
                    developerConnection.set("scm:git:https://github.com/navikt/sykepengesoknad-kafka.git")
                    url.set("https://github.com/navikt/sykepengesoknad-kafka")
                }
            }
            from(components["java"])
        }
    }
}

ktlint {
    version.set("1.5.0")
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs.add("-Xjsr305=strict")
        if (System.getenv("CI") == "true") {
            allWarningsAsErrors.set(true)
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
        jvmArgs("-XX:+EnableDynamicAgentLoading")
        testLogging {
            events("PASSED", "FAILED", "SKIPPED")
            exceptionFormat = FULL
        }
        failFast = false
    }
}
