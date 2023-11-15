import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


repositories {
    mavenCentral()
}

val kotlinVersion = "1.9.20"
val kluentVersion = "1.73"
val junitVersion = "5.10.1"

plugins {
    kotlin("jvm") version "1.9.20"
    id("java")
    id("maven-publish")
}

group = "no.nav.helse.flex"
version = properties["version"] ?: "local-build"
description = "sykepengesoknad-kafka"
java.sourceCompatibility = JavaVersion.VERSION_14

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.6.1")
    }
}

apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "org.jlleitschuh.gradle.ktlint")

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "14"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
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

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("STARTED", "PASSED", "FAILED", "SKIPPED")
        exceptionFormat = TestExceptionFormat.FULL
    }
    failFast = false
}
